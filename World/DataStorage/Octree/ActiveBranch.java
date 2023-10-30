package World.DataStorage.Octree;


/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  ActiveBranch
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * This branch is a small slice
 * of the octree that is loaded
 * into ram. This branch provides
 * methods for changing its loaded
 * location
 *
 * Based on the current size
 * and node location, I should
 * be able to find the branches
 * in directions relative to the
 * active branch
 *
 * Store 27 branches 1 branch for
 * the main center, and the others
 * for each direction
 */
public class ActiveBranch{
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Constructor
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     * Create an octree large enough
     * to hold all leaves in a zone,
     * and intertwine with a link list
     */

    int branchLoadingScale = 4;
    public ActiveBranch(int scale){
        Branch[][][] branches = new Branch[3][3][3];
        long coreBranchKey = 0;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Directional Loading
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Load branches at the current
     *  loading scale, and remove the
     *  branches in the opposite
     *  direction
     *
     *  Calculate branch keys relative
     *  to core branch or branches[1][1][1]
     */
    public void loadUp(){

    }
    public void loadDown(){

    }
    public void loadNorth(){

    }
    public void loadEast(){

    }
    public void loadSouth(){

    }

    public void loadWest(){

    }
}
