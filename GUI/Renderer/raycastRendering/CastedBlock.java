package GUI.Renderer.raycastRendering;

public class CastedBlock {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  CastedBlock
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * A CastedBlock block is a class
    * to store info about a block's
    * rendered details from the at
    * a screen location. Used for
    * organizing Rendered blocks for
    * cashing, and adding shadows
    */

    //The cords in the octree of the block
    private long[][] triangleBlockCords;//[0] = x, [1] = y, [2] = z.

    //The type of block it is.
    private int[][] triangleData; //[] block id, [][] = face
    private int[] triangleShadeData;

    //The location on the screen. The cast block is located
    private int[] screenCords; //[0] = x, //[1] = y
    //if the block is shaded

    public CastedBlock(int[] screenCords){
        this.screenCords = screenCords;
        this.triangleData = new int[2][2];
        this.triangleBlockCords = new long[2][3];
        this.triangleShadeData = new int[2];
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Cords
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Get the cords of the block
     * the triangle is rendered on
     */
    //set the cast blocks info
    public void setTriangleBlockCords(int triangle, long[] newCords){
        this.triangleBlockCords[triangle] = newCords;
    }

    public long[] getTriangleBlockCords(int triangle){
        return triangleBlockCords[triangle];
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Shaders
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * the faces shade
     */

    public void setTriangleShader(int triangle, int shader){
        triangleShadeData[triangle] = shader;
    }

    public int getTriangleShader(int triangle){
        return triangleShadeData[triangle];
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Texture
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Stores the block type
     * and triangle texture type
     */

    public void setTriangleTexture(int triangle, int[] newType){
        this.triangleData[triangle] = newType;
    }

    public int[] getTriangleTexture(int triangle){
        return triangleData[triangle];
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Screen cords
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Store the screen cords of
     * where on screen the block
     * is located.
     */

    public int[] getScreenCords(){
        return screenCords;
    }

    public int getScreenX(){
        return screenCords[0];
    }

    public int getScreenY(){
        return screenCords[1];
    }
}
