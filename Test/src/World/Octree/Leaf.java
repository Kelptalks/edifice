package World.Octree;
/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Leaf Class
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * This class is for interpreting, manipulating and debugging the bitPacked leaf which is the final level of the octree.
 */
public class Leaf{

    public Leaf(){
        //System.out.println("new leaf");
    }

    private long[] leafs = new long[512];
    private static final int BLOCKS_PER_LONG = 8;//Blocks contained in each bitPackedInt
    private static final int BITS_PER_BLOCK = 8;//How many bits each block takes up
    private static final int BLOCKS_PER_LEAF = 512*8;//How many blocks each block takes up

    //Returns an array of every block in the leaf.
    public int[] getLeaf(){
        //Create the set array for returning the blocks
        int[] blockSet = new int[BLOCKS_PER_LEAF];
        //unpacking and adding each block to the blockSet array
        for(int bitIndex = 0; bitIndex<BLOCKS_PER_LEAF; bitIndex++){
            blockSet[bitIndex] = (int) ((leafs[bitIndex/BLOCKS_PER_LONG]>>bitIndex*BITS_PER_BLOCK) & 0xFF);
        }
        return blockSet;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Traversal/Editing
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */

    //Returns block based off the key.
    public int getBlock(int linearKey) {
        int arrayIndex = linearKey >> 3;  // Equivalent to key / 8
        int bitIndex = linearKey & 0x7;   // Equivalent to key % 8
        return (int) ((leafs[arrayIndex] >> (bitIndex << 3)) & 0xFF);
    }

    public int getBlockFromKey(int key) {
        int linearKey = calculateLinearIndex(key);
        return getBlock(linearKey);
    }

    //change a block in the leaf
    public void setBlock(int key, int blockType){
        int arrayIndex = key >> 3;
        int bitIndex = key & 7;
        leafs[arrayIndex] = (leafs[arrayIndex] & ~(0xFFL << (bitIndex * BITS_PER_BLOCK))) | ((blockType & 0xFFL) << (bitIndex * BITS_PER_BLOCK));
    }

    public void setBlockFromKey(int key, int blockType) {
        int linearKey = calculateLinearIndex(key);
        setBlock(linearKey, blockType);
    }

    public int calculateLinearIndex(int key) {
        int x = (key & 0b100) >> 2;
        int y = (key & 0b010) >> 1;
        int z = (key & 0b001);

        return x * 4 + y * 2 + z;
    }

    public void setBlock(int x, int y, int z, int blockType){
        setBlock(x+y*16+z*64, blockType);
    }

    public int getBlock(int x, int y, int z){
        return getBlock(x+y*16+z*64);
    }

}