package World.DataStorage.Octree;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Octree
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 */

// ! need to add a test that checks if iver block in the tree is set to 0 exepet it.

public class Octree {

    private long maxVal;
    private int topDepth = 1;
    private Branch root = new Branch(1);

    public Octree() {
        calculateMax();
    }


    public void expand(int direction){
        Branch newRoot = new Branch(topDepth+1);
        newRoot.setBranch(root, direction);
        this.root = newRoot;
        topDepth++;
        calculateMax();
    }

    //IF this to take into leaf nodes
    private void calculateMax(){
        this.maxVal = (int) Math.pow(2, topDepth);
    }

    public long getMaxVal(){
        return maxVal;
    }

    public Branch getRoot(){
        return root;
    }

    public int getTopDepth(){
        return topDepth;
    }

    public int[] unpackOctreeKey(long key){
        //based off the key load a specific quadrant.
        //origin 0,0 then negatives and positives.
        int[] octreeCoords = new int[21];
        //mask key based on depth 3 bits forEvery depth increase
        //loop through key(21 is 64 bits/3 bits)
        for (int x = 20; x > -1; x--){
            octreeCoords[x] = (int) (key >> x*3) & 0x7;
        }
        return octreeCoords;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Block manipulation
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */

    //Get a block from the octree
    public int getBlock(long key){

        Branch branch = root;
        long OctreeKey = (key >> 12);
        //Cycle through octree until a depth of 1 is hit
        for (int x = topDepth-1; x > 0; x--){
            int branchIndex = (int) (OctreeKey >> 3*x) & 0x7; //this will get the index based off the branches depth
            Branch newBranch = branch.getBranch(branchIndex);
            if (newBranch == null){
                return 0;
            }
            branch = newBranch;
        }


        //get the leaf from depth 1 and set the block from depth 0
        int leafKey = (int) (OctreeKey & 0x7);
        Leaf leaf = branch.getLeaf(leafKey);
        if (leaf == null){
            return 0;
        }
        return leaf.getBlock((int) (key & 0x0FFF));
    }

    //Set a block in the octree will generate new branches if block
    public void setBlock(long key, int block){
        Branch branch = root;
        long OctreeKey = (key >> 12);
        //Cycle through octree until a depth of 1 is hit
        for (int x = topDepth-1; x > 0; x--){
            int branchIndex = (int) (OctreeKey >> 3*x) & 0x7; //this will get the index based off the branches depth
            Branch newBranch = branch.getBranch(branchIndex);
            if (newBranch == null){
                newBranch = new Branch(x);
                branch.setBranch(newBranch, branchIndex);
            }
            branch = newBranch;
        }

        //get the leaf from depth 1 and set the block from depth 0
        int leafKey = (int) (OctreeKey & 0x7);
        Leaf leaf = branch.getLeaf(leafKey);
        if (leaf == null){
            leaf = new Leaf();
            branch.setLeaf(leaf, leafKey);
        }
        leaf.setBlock((int) (key & 0x0FFF), block);
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Debugging
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */
    //print a text based tree.
    public void printTree(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        root.printTree();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    //use a scanner to take inputs and allow traversal and review of a text based tree
    public void navTree(){
        Branch currentBranch = root;
        int currentDepth = this.topDepth;
        currentBranch.navTree(root, topDepth);
    }

}
