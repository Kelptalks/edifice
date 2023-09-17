package Constants;

public class Constants{
    public static final int SCREEN_X_REZ = 1920;
    public static final int SCREEN_Y_REZ = 1080;

    public static final int BLOCKXREZ = 32;

    //chunk dimensions
    public static final int CHUNK_DIMENSION = 16;
    public static final int CHUNK_DIMENSION_SQUARED = CHUNK_DIMENSION*CHUNK_DIMENSION;//Stored to save on computation
    public static final int CHUNK_DIMENSION_CUBED = CHUNK_DIMENSION*CHUNK_DIMENSION*CHUNK_DIMENSION;

    //Array Dimentions of the Chunk
    public static final int ARRAY_DIMENSION = CHUNK_DIMENSION/4;
    public static final int ARRAY_DIMENSION_SQUARED = ARRAY_DIMENSION*ARRAY_DIMENSION;
    

    public static final int BLOCK_WIDTH_FACTOR = 32;
    public static final int BLOCK_HEIGHT_FACTOR = 16;
    
    //Bitpacking related variables.
    public static final int BLOCKS_PER_INT = 4;//Blocks contained in each bitPackedInt
    public static final int BITS_PER_BLOCK = 8;//How many bits each block takes up

    public static final int SETS_PER_LAYER = ARRAY_DIMENSION_SQUARED*BLOCKS_PER_INT;

}
