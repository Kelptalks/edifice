package World.DataStorage.Octree;

import World.TerrainGen.TerrainGen;

import java.io.Serializable;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Leaf Class
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * This class is for interpreting, manipulating and debugging the bitPacked leaf which is the final level of the octree.
 */
public class Leaf implements Serializable{

    public Leaf(){
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

    public void fill(int block){
        for (int x = 0; x < BLOCKS_PER_LEAF; x++){
            this.setBlock(x, block);
        }
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Traversal/Editing
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */

    //Returns block based off the key.
    public int getBlock(int key) {
        int arrayIndex = (key & 0xFFF) >> 3;  // Equivalent to key / 8
        int bitIndex = key & 0x7;   // Equivalent to key % 8
        return (int) ((leafs[arrayIndex] >> (bitIndex << 3)) & 0xFF);
    }

    //change a block in the leaf
    public void setBlock(int key, int blockType){
        int arrayIndex = (key & 0xFFF) >> 3;
        int bitIndex = key & 7;
        leafs[arrayIndex] = (leafs[arrayIndex] & ~(0xFFL << (bitIndex * BITS_PER_BLOCK))) | ((blockType & 0xFFL) << (bitIndex * BITS_PER_BLOCK));
    }

}