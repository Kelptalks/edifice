package World.ActiveBranch;

import World.Octree.Branch;
import World.Octree.KeyMod;
import World.Octree.Octree;
import World.World;

public class ActiveArea {

    private Branch activeBranches[][][];
    private long coreKey;
    private int branchLoadingScale;
    private World world;
    private KeyMod keyMod;

    public ActiveArea(World world, long coreKey, int branchLoadingScale){
        //set up area
        activeBranches = new Branch[3][3][3];

        //set up values
        this.world = world;
        this.coreKey = coreKey;
        this.branchLoadingScale = branchLoadingScale;
        this.keyMod = new KeyMod();

        //Load active area around core
        setUp();
        setBranchDimension();
    }

    //Set up the active area
    public void setUp() {
        //load 3 Z planes around the core
        activeBranches[1][1][1] = world.loadBranch(coreKey, branchLoadingScale);

        //set up relative center cords for block retrieval/editing
        setAreaActiveCenterCords();
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Directional Loading
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Load branches at the current
     * loading scale, and remove the
     * branches in the opposite
     * direction
     *
     * Calculate branch keys relative
     * to core branch or branches[1][1][1]
     *
     * Plane Loading Explanation:
     * Will get a plane relative to the coreKey based on
     * an axis.
     *
     * For example, on the z axis,
     * A relative Z cord of 1 would be the 3 by 3 area above the core,
     * A relative Z cord of 0 would be the same 3 by 3 plane around the core,
     * and a relative Z cord of -1 would be the plane bellow the core.
     */
    public void loadDirection(String direction){

    }

    //get the z Core
    public Branch[][][] getZPlane(int relativeZ){
        Branch[][][] relativeTopPlane = new Branch[3][3][3];
        //Cycle through the top plane
        for (int relativeY = -1; relativeY < 2; relativeY++){
            for (int relativeX = -1; relativeX < 2; relativeX++){
                //get the branch relative to the core
                relativeTopPlane[relativeX + 1][relativeY + 1][relativeZ + 1] = getRelativeBranch(relativeX, relativeY, relativeZ);
            }
        }
        return relativeTopPlane;
    }

    //returns a branch relative to the core
    public Branch getRelativeBranch(int x, int y, int z){
        long key = keyMod.getRelativeKey(coreKey, branchLoadingScale, x, y, z);
        return world.loadBranch(key, branchLoadingScale);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Directional Shifting
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Shifting the active branch based
     * off a direction, and add a null plane
     * to the direction expanded
     */
    public void shift(String direction){

    }

    //
    public void shiftZ(){

    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * single blocks
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Getting blocks
     */

    private long branchDimension;
    private long branchCenterKey;
    private void setBranchDimension() {
        Octree octree = new Octree(0);
        branchDimension = octree.getDimension(branchLoadingScale) / 2;
        branchCenterKey = keyMod.getRelativeKey(0, branchLoadingScale, branchDimension, branchDimension, branchDimension);
    }


        long activeAreaCenterKey;
    public void setAreaActiveCenterCords(){
        //get the detentions of one branch and multiply by activeZone dimensions(3)
        long activeAreaDimensions = (long) Math.pow(8, 2L << branchLoadingScale);
        //Divide by 2 to get the center
        this.activeAreaCenterKey = activeAreaDimensions/2;
        //print
        System.out.println("ActiveAreaCenterCor : " + activeAreaCenterKey);
    }

    public int getBlock(long x, long y, long z) {
        long relativeKey = keyMod.getRelativeKey(branchCenterKey, 0, x, y, z);
        return activeBranches[1][1][1].getBlock(relativeKey);
    }

    public void setBlock(long x, long y, long z, int block) {
        long relativeKey = keyMod.getRelativeKey(branchCenterKey, 0, x, y, z);
        activeBranches[1][1][1].setBlock(relativeKey, block);
    }

    public void setBlock(long key, int block){
        activeBranches[1][1][1].setBlock(key, block);
    }
}



