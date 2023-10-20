package World.TerrainGen;
import World.Octree.Branch;
import World.Octree.Octree;
/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Testing Terrain
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Create a testing terrain for testing ray cast rendering
 *  and basic block manipulation
 */
public class TestingTerrain {

    //Create a 1000 b^3 testing area

    //It takes a bit to generate this, so I may want to
    //Create an octree loading system, where I set the root
    //To prefab octree from file.
    public void genTerrain(Octree octree){
        //expand octree and ignore all origin settings
        //and extra stuff

        octree.expand(1);
        octree.expand(1);
        octree.expand(1);
        octree.expand(1);
        octree.expand(1);

        //Increment through every branch in origin 1-4 and set all the leaves in the branches to solid stone
        octree.getRoot().populateWithBranches();

        //536870912 blocks to fill the bottom four octrees
        for (int x = 0; x < 536870912; x++){
            octree.setBlock(x, 1);
        }
    }
}