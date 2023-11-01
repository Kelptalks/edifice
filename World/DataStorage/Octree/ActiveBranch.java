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
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Constructor
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     * Create an octree large enough
     * to hold all leaves in a zone,
     * and intertwine with a link list
     */

    int branchLoadingScale = 7; //the depth at which the active branch loads octrees from 128

    long coreKey; //the core key is an octree key linked to the core location
    Branch[][][] branches;
    public ActiveBranch(){
        this.branches = new Branch[3][3][3];
        this.coreKey = 0;
    }

    //set the core key
    public void setCoreKey(long key){
        this.coreKey = key;
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
    //x++
    public void loadNorth(){

    }
    public void loadEast(){

    }
    public void loadSouth(){

    }

    public void loadWest(){

    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Relative key coords
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Get keys relative to input
     * key
     */

    //Y++
    public long posYKey(Long key){
        //based on the branchLoadingScale create a relative key,
        //it also depends on what parent in the octree the key is.
        //keys in the sector located in along.


        //find starting sector
        int index = (int) ((key >> (3 * branchLoadingScale)) & 0x07);
        System.out.println(index);



        return key;
    }
}
