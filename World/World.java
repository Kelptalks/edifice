package World;

import World.DataStorage.Octree.Octree;
import World.TerrainGen.TerrainGen;
import World.TerrainGen.TestingTerrain;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  World
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Class to manage the game world
 *  and generate terrain
 */
public class World{
    private TerrainGen terrainGen = new TerrainGen();
    private TestingTerrain testingTerrain = new TestingTerrain();
    private Octree octree = new Octree();


    public World(){
        testingTerrain.genTerrain(octree);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Octree editing
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  I will need to create methods that can organize
     *  and track how the octree expands
     */
    public int getBlock(long x, long y, long z){
        return octree.getBlock(corToKey(z, y, x));
    }

    //set a block using with 3D cords create a negative system
    public void setBlock(long x, long y, long z, int block){
        octree.setBlock(corToKey(z, y, x), block);
    }

    private long corToKey(long x, long y, long z){
        int topDepth = octree.getTopDepth()+4; //add 4 because octree doesn't account for leave nodes
        long currentX = x;
        long currentY = y;
        long currentZ = z;

        long nodeSize = octree.getMaxVal();

        long key = 0;


        //find where the cords are relative to the center
        for (int depth = 0; depth <= topDepth; depth++) {
            long index = 0;

            // Check which half of the current node each coordinate falls into
            long halfSize = nodeSize / 2;

            if (x >= halfSize) {
                index |= 4;  //Set the x bit
                x -= halfSize;  //Update x for the next depth
            }

            if (y >= halfSize) {
                index |= 2;  //Set the y bit
                y -= halfSize;  //Update y for the next depth
            }

            if (z >= halfSize) {
                index |= 1;  //Set the z bit
                z -= halfSize;  //Update z for the next depth
            }

            // Shift for lower levels
            key |= (index << (3 * depth));

            // Update the nodeSize for the next depth level
            nodeSize = halfSize;
        }
        System.out.println(key);
        return key;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Generating Terrain
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Generating terrain based off a location
     */

    private void generate(int x, int y, int z){

    }
}
