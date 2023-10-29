package Testing.PackageTests;
import Testing.TestingTemplate;
import World.DataStorage.Octree.Leaf;
import World.DataStorage.Octree.Octree;

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
        octreeExtremeDepthsTest();
        testSingleBlockModification();

        System.out.println("~~~~~~~~~~~~~~~~~~~~");
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
        //Create octree
        Octree octree = new Octree();

        octree.expand(1);
        octree.expand(1);

        int key = 512; //key of octree
        int block = 1; //block to set key
        int totalBlocks = 262144; //the total blocks in this expanded octree

        octree.setBlock(key, block); //set block in octree

        //check that only one block in the octree was changed
        int blocksChanged = 0;
        for (int x = 0; x < totalBlocks; x++){
            if (octree.getBlock(x) != 0){
                blocksChanged++;
            }
        }

        //print test details to terminal
        if (blocksChanged == 1){
            System.out.println("-(pass) set and get test passed");
        }
        else {
            System.out.println("-(fail) set and get test failed");
            System.out.println(" Expected 1 block modified but " + blocksChanged + " were changed");
        }
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
        //Create octree
        Octree octree = new Octree();

        //Expand octree to an extreme depth
        int expansion = 15; //times to expand
        for (int x = 0; x < expansion; x++){
            octree.expand(1);
        }

        //get start time
        long t1 = System.nanoTime();

        //set 10,000,000/5 blocks in the octree
        long blocks = 10000000L;;
        for (int x = 0; x < blocks; x+=5){
            octree.setBlock(x, 12);
        }

        //calculate time taken
        long timeToComplete = (System.nanoTime() - t1)/(1000000);

        // Check for errors in block setting/getting
        boolean test = true;
        for (int x = 0; x < blocks; x+=5){

            // Check that all intended blocks are modified correctly
            if ((x%5 == 0) && (octree.getBlock(x) != 12)){
                System.out.println("-(fail) Octree extreme depths test Failed when adding block at octree key : " + x);
                System.out.println("Expected value 12 but was " + octree.getBlock(x));
                test = false;
                break;
            }

            // Check that all unintended blocks remain unmodified
            if ((x%5 != 0) && octree.getBlock(x) != 0){
                System.out.println("-(fail) Octree extreme depths test Failed when adding block at octree key : " + x);
                System.out.println("Expected value 0 but was " + octree.getBlock(x));
                test = false;
                break;
            }
        }

        //if no errors where found print pass
        if (test){
            System.out.println("-(pass) octree extreme depths test");
        }
    }
}
