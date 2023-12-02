package World.Octree;

import GameData.GameData;
import World.BlockEntity.BlockEntity;
import World.BlockEntity.Terp.Terp;

import java.io.Serializable;
import java.util.ArrayList;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Leaf Class
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * This class is for interpreting, manipulating and debugging the bitPacked leaf which is the final level of the octree.
 */
public class Leaf implements Serializable{

    private long[] leafs = new long[512];
    private ArrayList<BlockEntity> blockEntities = new ArrayList<BlockEntity>();
    private GameData gameData;

    public Leaf(GameData gameData){
        this.gameData = gameData;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Leaf Class
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Class constants
     */
    private static final int BITS_PER_BLOCK = 8;//How many bits each block takes up
    private static final int BLOCKS_PER_LEAF = 512*8;//How many blocks each block takes up

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
        if (blockType == 11){
            blockEntities.add(new Terp(gameData, key));
        }
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * block Entity retrieval
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */
    public BlockEntity getBlockEntity(long key){
        for (BlockEntity block : blockEntities){
            if (block.getLocation() == key){
                return block;
            }
        }
        return null;
    }
}