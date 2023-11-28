package Testing.Tests.Octree;
import World.Octree.Branch;
import World.Octree.Octree;

import java.util.Arrays;

import static java.lang.Integer.toBinaryString;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Octree Testing
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 * A set of tests used to debug and
 * unit test the octree class
 */

public class OctreTesting {

    public void run(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~ Octree Testing ~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~");

        //run tests
        testPopulate();
        System.out.println(" ");
        testLoadBranch();
        System.out.println(" ");
        testSavingBranch();


        System.out.println("~~~~~~~~~~~~~~~~~~~~");
    }

    public void testPopulate(){
        Octree octree = new Octree(9);
        octree.populate(octree.getRoot());
        System.out.println("-(pass) populate test");
    }
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Branch access
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Test the accessing of branches at different
     * depths, and verify keys are working.
     * Branches should be accessed based on the
     * key and there the desired depth.
     */
    public void testLoadBranch() {
        //setUp
        Octree octree = new Octree(9);
        octree.populate(octree.getRoot());
        int key = 293823232;
        int depth = 5;

        //run test
        Branch returnedBranch = octree.loadBranch(key, depth);

        //verify results
        //if the expected depth does not equal the returned branches depth
        if (returnedBranch.getDepth() != depth){
            System.out.println("Key : " + toBinaryString(key));
            System.out.println("Returned Branch depth(" + returnedBranch.getDepth() + ") | Expected(" + depth + ")");
        }

        //if the expected depth does not equal the returned branches depth
        else{
            System.out.println("-(pass) branch loading");
        }
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Branch saving/loading
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Test the saving and loading of branches
     * from file.
     *
     * Note:
     * The depth of saved branches
     * needs to be based upon the depth of the
     * active branch loader.
     */
    public void testSavingBranch(){
        //setup
        Octree octree = new Octree(7);
        octree.populate(octree.getRoot());
        String fileName = "test";
        boolean unloadedTest = false;

        //sav branch to file
        octree.getRoot().saveBranch(fileName);

        //Check file was unloaded
        if (octree.getRoot().getBranches() == null){
            unloadedTest = true;
        }

        //load branch from file
        octree.getRoot().loadBranch(fileName);

        //verify results
        if (octree.getRoot().getBranches() != null && unloadedTest){
            System.out.println("-(pass) Saving/Loading Branches");
        }
        else{
            System.out.println("-(fail) SavingBranch");
            System.out.println("After unloading : " + unloadedTest + " | Expected true");
            System.out.println("After saving branches were :" + Arrays.toString(octree.getRoot().getBranches()) + "| expected !null" );
        }

    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Testing block setting and getting
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Using an octree key test if the proper
     * block is set in the propper location,
     * and that it is the sole block to be
     * modified.
     *
     * In other words, check a block
     * is being set where it should be, and
     * is not causing other blocks to change
     */
    public void testSingleBlockModification(){

    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Test block adding at extreme depths
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * This test is to test octree at extreme
     * depths this is accomplished by expanding
     * the octree to a depth of over 10 and
     * then filling a set of keys with blocks.
     * This test also helps detect the accuracy
     * of locations by setting blocks every
     * five, and when checking that un modified
     * blocks are unmodified.
     */
    public void octreeExtremeDepthsTest(){

    }
}
