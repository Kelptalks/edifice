package World.DataStorage.Octree;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Octree
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * I would like this octree version
 * to have a solid coordinate system
 * build in the idea of relative to
 * center cords
 *
 *
 */

import java.util.Timer;

public class Octree {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Constructor
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */
    public Octree(int depth){
        this.depth = depth;
        this.root = new Branch(depth);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Octree Info
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */
    private int depth;
    private Branch root;
    private final int nodeSize = 8;

    // Get the octree side length in blocks
    public int getDimension(){
        return 2 << depth-1; //exponential function
    }

    // Get the octree volume
    public long getVolume(){
        return (long) Math.pow(getDimension(), 3); //cube the length
    }

    //get the root branch
    public Branch getRoot(){
        return this.root;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Branch Management
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */

    public void populate(Branch branch){
        int currentDepth = branch.getDepth();

        //if the branches depth on branch level
        if (branch.getDepth() > 4){
            for (int x = 0; x < nodeSize; x++){
                Branch newBranch = new Branch(currentDepth - 1);
                populate(newBranch);
                branch.setBranch(newBranch, x);
            }
        }

        //if the branches depth is on leave level
        if (branch.getDepth() == 4){
            for (int x = 0; x < nodeSize; x++) {
                Leaf newLeaf = new Leaf();
                branch.setLeaf(newLeaf, x);
            }
        }
    }

    //set the branch currently loaded.
    public void setActiveBranch(Branch branch){
        //will load a branch from file

    }

    //save a branch to file
    public void saveBranch(Branch branch){
        //will save a branch to file
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Block Management
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */

    public void setBlock(int key, int block) {
    }

    public int getBlock(int x) {
        return 1;
    }

}
