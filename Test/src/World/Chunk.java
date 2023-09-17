package World;
import Constants.Constants;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Chunk class
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* This class is for interpreting, miniupulating and debugging the bitPacked Chunk Data array It contains. 
*/
public class Chunk {
    //Object variables
    private int[] chunkData;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Constructor
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public Chunk(){
        //calculating the array sized required for chunks packed to 8 bits
        int size = (Constants.CHUNK_DIMENSION_CUBED)/Constants.BLOCKS_PER_INT;
        this.chunkData = new int[size];
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  ChunkData edit methods
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  These methods are used to edit values in the chunk data array.
     * 
     */

     
    //Returns the block type at a specific block location
    public void setBlock(int blockLocation, int blockType){
        int arrayIndex = blockLocation/Constants.BLOCKS_PER_INT;
        int bitIndex = blockLocation%Constants.BLOCKS_PER_INT;

        // Clear existing 8 bits in the current value and replace with blockType
        chunkData[arrayIndex] = (chunkData[arrayIndex] & ~(0xFF << (bitIndex * Constants.BITS_PER_BLOCK))) | ((blockType & 0xFF) << (bitIndex * Constants.BITS_PER_BLOCK));
        
    }

    //Returns the block type based off block cords
    public void setBlock(int x, int y, int z, int blockType){
        int blockLocation = getBlockLocation(x, y, z);
        int arrayIndex = blockLocation/Constants.BLOCKS_PER_INT;
        int bitIndex = blockLocation%Constants.BLOCKS_PER_INT;

        // Clear existing 8 bits in the current value and replace with blockType
        chunkData[arrayIndex] = (chunkData[arrayIndex] & ~(0xFF << (bitIndex * Constants.BITS_PER_BLOCK))) | ((blockType & 0xFF) << (bitIndex * Constants.BITS_PER_BLOCK));
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  ChunkData return methods
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Methods for unpacking the chunk data.
     * Methods are orginised in order from small to large scale.
     * 
     * Things to add : methods of exporting multi dimentional array versions of each chunk. 
     */


    //Return block 1D location in chunk
    private int getBlockLocation(int x, int y, int z){
        //convert cords into block num in chunk
        return x + y*Constants.CHUNK_DIMENSION + z*Constants.CHUNK_DIMENSION_SQUARED;
    }

    //Returns the block type at a specific location
    private int unpackBlock(int blockLocation){
        int arrayIndex = blockLocation/Constants.BLOCKS_PER_INT;
        int bitIndex = blockLocation%Constants.BLOCKS_PER_INT;
        return(chunkData[arrayIndex]>>bitIndex*Constants.BITS_PER_BLOCK) & 0xFF;
    }

    //UnPacks 1 intiger in the array giving a set of 4 blocks
    private int[] unpackBlockSet(int arrayIndex){
        //Create a the set array for returning the blocks
        int[] blockSet = new int[Constants.BLOCKS_PER_INT];
        //unpacking and adding each block to the blockSet array
        for(int bitIndex = 0; bitIndex<Constants.BLOCKS_PER_INT; bitIndex++){
            blockSet[bitIndex] = (chunkData[arrayIndex]>>bitIndex*Constants.BITS_PER_BLOCK) & 0xFF;
        }
        return blockSet;
    }

    //Unpacks a 1 horizontal layer of the chunk
    public int[] unpackLayer(int z){
        //Create an array the size of one chunk layer
        int[] blockLayer = new int[Constants.CHUNK_DIMENSION_SQUARED];
        
        //get all sets of blocks on said layer and add them to an array
        for(int x = 0; x<Constants.SETS_PER_LAYER; x++){
            int unpackBlockSet[] = unpackBlockSet(x+z*Constants.SETS_PER_LAYER);
            
            System.arraycopy(unpackBlockSet, 0, blockLayer, x*Constants.BLOCKS_PER_INT, Constants.BLOCKS_PER_INT);
        }
        return blockLayer;
    }

    //Returns a full unpacked version of the entire chunk
    public int[] unpackChunk(){
        int[] chunkData = new int[Constants.CHUNK_DIMENSION_CUBED];
        for(int z = 0; z<Constants.CHUNK_DIMENSION; z++){
            System.arraycopy(unpackLayer(z), 0, chunkData, z*Constants.CHUNK_DIMENSION_SQUARED, Constants.CHUNK_DIMENSION_SQUARED);
        }
        return chunkData;
    }

    public int[] unpackRow1D(int y, int z){
        int[] blockRow = new int[16];
        for(int x = 0; x<4; x++){
            int unpackBlockSet[] = unpackBlockSet(x+y*Constants.BLOCKS_PER_INT+z*Constants.SETS_PER_LAYER);
            System.arraycopy(unpackBlockSet, 0, blockRow, x*Constants.BLOCKS_PER_INT, Constants.BLOCKS_PER_INT);
        }
        return blockRow;
    }

    //Unpacks a 1 horizontal layer of the chunk and returns a 2D array
    public int[][] unpackLayer2D(int z){
        //Create an array the size of one chunk layer
        int[][] blockLayer = new int[Constants.CHUNK_DIMENSION][Constants.CHUNK_DIMENSION];
        //get all sets of blocks on said layer and add them to an array
        for(int y = 0; y<Constants.CHUNK_DIMENSION; y++){
            blockLayer[y] = unpackRow1D(y, z);
        }
        return blockLayer;
    }

    //Returns a full unpacked version of the entire chunk and returns a 3D array
    public int[][][] unpackChunk3D(){
        int[][][] chunkData = new int[Constants.CHUNK_DIMENSION][Constants.CHUNK_DIMENSION][Constants.CHUNK_DIMENSION];
        for(int z = 0; z<Constants.CHUNK_DIMENSION; z++){
            chunkData[z] = unpackLayer2D(z);
        }
        return chunkData;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Debugging methods
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Methods used for debugging 
     */

    public void printBlock(int x, int y, int z){
        System.out.println(unpackBlock(getBlockLocation(x, y, z)));
    }

    public void printlayer(int z){
        int[] blockLayer = unpackLayer(z);
        System.out.println(" hello");
        for(int y = 0; y<Constants.CHUNK_DIMENSION; y++){
            for(int x = 0; x<Constants.CHUNK_DIMENSION; x++){
                System.out.print(blockLayer[x+y*Constants.CHUNK_DIMENSION]);
            }
            System.out.println('|');
        }
    }
}
