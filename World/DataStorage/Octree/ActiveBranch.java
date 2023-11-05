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

    //the scale at which branches are loaded.
    private int branchLoadingScale = 7; //the depth at which the active branch loads octrees from 128

    //key manipulation tool
    private KeyMod keyMod = new KeyMod();

    //octree
    private Octree octree = new Octree(20);

    //the core key is an octree key linked to the core location
    private long coreKey;
    Branch[][][] activeArea;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Constructor
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     * Create an octree large enough
     * to hold all leaves in a zone,
     * and intertwine with a link list
     */

    public ActiveBranch(){
        this.activeArea = new Branch[3][3][3];

        //set the center octree
        setCoreKey(576460750000000000L);
        setBranchVolume();
        loadUp();
        loadUp();
        loadUp();
    }

    //set the core key
    public void setCoreKey(long key){
        this.coreKey = key;

        //load area around core
        activeArea[0][0][0] = octree.loadBranch(key, branchLoadingScale);
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
        //shift an array down

        //cycle through plane and shift it down one.
        for (int z = 1; z < 3; z++){
            for (int y = 0; y < 3; y++){
                for (int x = 0; x < 3; x++){
                    activeArea[x][y][z - 1] = activeArea[x][y][z];
                }
            }
        }

        for (int y = 0; y < 3; y++){
            for (int x = 0; x < 3; x++){
                long key = keyMod.getRelativeKey(coreKey, branchLoadingScale, x - 1, y - 1, 1);
                activeArea[x][y][2] = octree.loadBranch(key, branchLoadingScale);
            }
        }
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Key to Cord conversion
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Converting x, y, z cords to
     * relative key cords in the
     * active branch
     */

    //the volume of each branch in the tree.
    private int branchDimension = 0;

    private void setBranchVolume(){
        branchDimension = (int) octree.getDimension(branchLoadingScale);
    }

    public void corToKey(int xCor, int yCor, int zCor){
        //find what branch node the cords are in
        int x = xCor / branchDimension;
        int y = yCor / branchDimension;
        int z = zCor / branchDimension;

        Branch branch = activeArea[x][y][z];

        //create a key for that a block in that branch
        x = xCor % branchDimension;
        y = yCor % branchDimension;
        z = zCor % branchDimension;

        long key = keyMod.getRelativeKey(0,0, x % branchDimension, y, z);
        int block = branch.getBlock(key);

        System.out.println(block);
    }

}
