package World.DataStorage.Octree;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Octree
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * This octree is designed to
 * load branches from file
 *
 */

import java.util.Timer;

import static java.lang.Integer.toBinaryString;

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

    public void getBranchAtDepth(long Key, int depth){

    }

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

    //!WARNING!
    // depths < 4 not allowed!
    // as leaf objects cannot be accessed through the
    // branch loading function.

    //load a branch from disk
    public Branch loadBranch(long key, int depth){
        //get staring branch
        Branch branch = this.root;

        //cycle through depths, stop when the desired depth is reached
        for (int currentDepth = this.depth; currentDepth > depth; currentDepth--) {
            //get 3 bits of the key based on depth
            int index = (int) ((key >> (3 * currentDepth)) & 0x07);

            //return the branch with that key
            branch = branch.getBranch(index);
        }
        return branch;
    }

    //save a branch to disk
    public void unloadBranch(Branch branch){

    }
}
