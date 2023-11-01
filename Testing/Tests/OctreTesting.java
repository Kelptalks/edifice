package Testing.Tests;
import Testing.TestingTemplate;
import World.DataStorage.Octree.Branch;
import World.DataStorage.Octree.Octree;

import static java.lang.Integer.toBinaryString;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Octree Testing
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Spencer Frank
 *
 * A set of tests used to debug and
 * unit test the octree class
 */

public class OctreTesting extends TestingTemplate {

    @Override
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
        int depth = 4;

        //run test
        Branch returnedBranch = octree.loadBranch(key, depth);

        //verifie results
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
        Octree octree = new Octree(9);
        octree.populate(octree.getRoot());
        String fileName = "test";


        //test saving branch to file
        octree.getRoot().saveBranch(fileName);
        System.out.println(fileName + " Saved");

        //test loading branch from file
        octree.getRoot().loadBranch(fileName);
        System.out.println(fileName + " unloaded");
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
