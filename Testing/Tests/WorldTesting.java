package Testing.Tests;

import GameData.GameData;
import World.World;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * World testing
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Testing world cord conversion
 * and world generation
 */
public class WorldTesting {

    public void run(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~ World Testing ~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~");

        //run tests
        testSingleBlockModification();

        System.out.println("~~~~~~~~~~~~~~~~~~~~");
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Test block modifications
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Using a 3D cord test if the proper
     * block is set in the propper location,
     * and that it is the sole block to be
     * modified.
     *
     * In other words, check a block
     * is being set where it should be, and
     * is not causing other blocks to change
     */
    public void testSingleBlockModification(){
        //World world = new World();
    }
}
