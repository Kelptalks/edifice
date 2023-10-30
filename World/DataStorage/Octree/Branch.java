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
    private final int depth;
    public Branch(int depth){
        this.depth = depth;
        allocate();
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Both
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */

    //Declares ether the Leaf array or Branch array, but does not populate.
    public void allocate() {
        if (depth == 1) {
            leaves = new Leaf[8];
        } else {
            branches = new Branch[8];
        }
    }

    //Fills up arrays with objects based on depth
    public void populate() {
        if (depth == 1) {
            populateWithLeafs();
        } else {
            populateWithBranches();
        }
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Leaf methods
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */
    //fill the branch with leafs
    public void populateWithLeafs() {
        for (int i = 0; i < leaves.length; i++) {
            leaves[i] = new Leaf();
        }
    }

    public Leaf getLeaf(int cords){
        return leaves[cords];
    }

    public void setLeaf(Leaf leaf, int index){
        leaves[index] = leaf;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Branch methods
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */
    public void populateWithBranches() {
        for (int i = 0; i < branches.length; i++) {
            branches[i] = new Branch(depth - 1);
        }
    }

    public Branch getBranch(int cords){
        return branches[cords];
    }

    public void setBranch(Branch branch, int index){
        branches[index] = branch;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Debugging
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */


    //Use a scanner object to allow navigation of the tree.
    public void navTree(Branch currentBranch, int depth){
        Scanner scanner = new Scanner(System.in);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Current Depth : " + depth);
        System.out.println("Branches : ");
        if (depth > 1){
            for (int x = 0; x < 8; x++){
                if (currentBranch.getBranch(x) != null){
                    System.out.println(x + "." + currentBranch.getBranch(x));
                }
            }
            //read and select if there are branches
            System.out.println(" ");
            System.out.println("Select Branch Index : " );
            int input = scanner.nextInt();


            currentBranch = currentBranch.getBranch(input);
            depth--;
            currentBranch.navTree(currentBranch, depth);
        }
        //print the leaves and exit
        else{
            for (int x = 0; x < 8; x++){
                if (getLeaf(x) != null){
                    System.out.println(x + "." + getLeaf(x));
                }
            }
        }
    }

    //print all tree data in text
    public void printTree(){
        if (depth == 1) {
            for (Leaf leaf : leaves) {
                for (int t = 0; t < depth; t++){
                    System.out.print("   ");
                }
                if (leaf != null) {
                    System.out.print(" -Leaf ");
                }
            }
            System.out.println(" ");
        }
        else {
            int x = 0;
            for (Branch branch : branches) {
                if (branch != null) {
                    for (int t = 0; t < depth; t++){
                        System.out.print("   ");
                        System.out.println("   ");
                    }
                    System.out.println("-Branch at index " + (x) + " | Depth : " + depth);
                    branch.printTree();
                }
                x++;
            }
        }
    }
}