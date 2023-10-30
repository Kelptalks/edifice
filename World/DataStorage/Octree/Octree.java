package World.DataStorage.Octree;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Octree
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * This octree is designed to
 * load branches from file
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
    public long getDimension(){
        return 2L << depth-1; //exponential function
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

    }

    //save a branch to file
    public void unloadBranch(Branch branch){

    }
}
