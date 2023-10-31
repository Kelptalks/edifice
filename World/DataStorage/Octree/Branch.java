package World.DataStorage.Octree;



import java.util.Scanner;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Branch class
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * is responsible for storing ether more branches or more leaves.
 */
public class Branch{
    private Branch[] branches;
    private Leaf[] leaves;
    private int depth;

    Branch(int depth){
        this.depth = depth;
        if (depth == 4){
            this.leaves = new Leaf[8];
        }
        else{
            this.branches = new Branch[8];
        }
    }

    public void setBranch(Branch branch, int index){
        branches[index] = branch;
    }

    public void setLeaf(Leaf newLeaf, int index) {
        leaves[index] = newLeaf;
    }

    public int getDepth(){
        return this.depth;
    }
}