package Constants;

public class Constants{
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Screen rez
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public static final int SCREEN_X_REZ = 1920;
    public static final int SCREEN_Y_REZ = 1080;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Chunk dimensions
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */

    public static final int CHUNK_DIMENSION = 16;
    public static final int CHUNK_DIMENSION_SQUARED = CHUNK_DIMENSION*CHUNK_DIMENSION;//Stored to save on computation
    public static final int CHUNK_DIMENSION_CUBED = CHUNK_DIMENSION*CHUNK_DIMENSION*CHUNK_DIMENSION;



    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  ChunkRendering
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public static final int BLOCK_WIDTH_FACTOR = 32;
    public static final int BLOCK_HEIGHT_FACTOR = 16;
    public static final int TRIANGLES_PER_BLOCK = 6;

}
