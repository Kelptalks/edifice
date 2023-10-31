package Testing.Tests;
import Testing.TestingTemplate;
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
        testPopulate();
        testGetInfo();
        //octreeExtremeDepthsTest();
        //testSingleBlockModification();

        System.out.println("~~~~~~~~~~~~~~~~~~~~");
    }

    public void testPopulate(){
        Octree octree = new Octree(9);

        System.out.println(octree.getDimension());
        System.out.println(octree.getVolume());

        octree.populate(octree.getRoot());
    }
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Test octree info
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public void testGetInfo() {
        Octree octree = new Octree(21);
        System.out.println(octree.getDimension());
        System.out.println(octree.getVolume());
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
