package World.DataStorage.Octree;



import java.io.*;
import java.util.Scanner;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Branch class
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * is responsible for storing ether more branches or more leaves.
 */
public class Branch implements Serializable {
    private Branch[] branches;
    private Leaf[] leaves;

    private String file;
    private final int depth;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Constructor
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * initialized the correct array-based
     * depth, depths below 4 are leaf branches
     */
    Branch(int depth){
        this.depth = depth;
        if (depth == 4){
            this.leaves = new Leaf[8];
        }
        else{
            this.branches = new Branch[8];
        }
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  getter/setters
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * For getting and setting values
     */
    public void setBranch(Branch branch, int index){
        branches[index] = branch;
    }

    public Branch getBranch(int index){
        return branches[index];
    }

    public Branch[] getBranches(){
        return branches;
    }

    public void setLeaf(Leaf newLeaf, int index) {
        leaves[index] = newLeaf;
    }

    public Leaf[] getLeaves(){
        return leaves;
    }

    public int getDepth(){
        return this.depth;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Traversal
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * For traversing the branch
     */

    //get block from octree cords
    public int getBlock(long key){
        int index = (int) ((key >> (3 * this.depth)) & 0x07);
        if (depth > 4 && branches[index] != null){
            return branches[index].getBlock(key);
        }
        else if (depth == 4) {
            return leaves[index].getBlock((int) key);
        }
        return 0;
    }

    //get block from octree cords
    public void setBlock(long key, int block){
        int index = (int) ((key >> (3 * this.depth)) & 0x07);
        if (depth > 4 && branches[index] != null){
            branches[index].setBlock(key, block);
        }
        else if (depth == 4) {
            leaves[index].setBlock((int) key, block);
        }
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  File management
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * For getting and setting values
     */

    //Save a branch to file
    public void saveBranch(String fileName){
        //save to file
        file = fileName;

        //save branch to file
        File directory = new File("World/DataStorage/Octree/SaveData");

        // Save to file in that directory
        String filePath = directory.getAbsolutePath() + File.separator + fileName;
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //unload branch
        branches = null;
        leaves = null;
    }

    //load a branch from file
    public void loadBranch(String fileName) {
        //
        Branch loadedBranch = null;
        File directory = new File("World/DataStorage/Octree/SaveData");
        String filePath = directory.getAbsolutePath() + File.separator + fileName;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            loadedBranch = (Branch) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Set branch data from loaded branch data
        if (loadedBranch.getDepth() == 4){
            this.leaves = loadedBranch.getLeaves();
        }
        else {
            this.branches = loadedBranch.getBranches();
        }
    }
}