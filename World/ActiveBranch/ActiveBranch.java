package World.ActiveBranch;

import GameData.GameData;
import World.Octree.Branch;
import World.Octree.KeyMod;
import World.Octree.Octree;
import World.World;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  ActiveBranch
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * This branch is a small slice
 * of the octree that is loaded
 * into ram. This branch provides
 * methods for changing its loaded
 * location
 *
 * Relative keys:
 * Based on the current size
 * and node location, I should
 * be able to find the branches
 * in directions relative to the
 * active branch
 *
 * Cashing:
 * Store 27 branches in a 3D
 * array of 3 by 3 by 3
 * Array[1][1][1] is the core
 *
 * Saving/Loading:
 * The depth of the octree the
 * active branch loads from
 * is also the depth at which branches
 * are saved to file.
 */
public class ActiveBranch{

    //the scale at which branches are loaded.
    private int branchLoadingScale = 7; //the depth at which the active branch loads octrees from 128

    //key manipulation tool
    private final KeyMod keyMod = new KeyMod();

    //octree
    private Octree octree;

    private ActiveArea activeArea;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Constructor
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     * Create an octree large enough
     * to hold all leaves in a zone,
     * and intertwine with a link list
     */

    public ActiveBranch(World world){
        this.octree = new Octree(20);
        this.activeArea = new ActiveArea(world, 576460750000000000L, branchLoadingScale);
    }

    //return a block relative to the center of the core
    public int getBlock(long xCor, long yCor, long zCor){
        return activeArea.getBlock(xCor, yCor, zCor);
    }

    public void setBlock(long xCor, long yCor, long zCor, int BlockType){
        activeArea.setBlock(xCor, yCor, zCor, BlockType);
    }

}
