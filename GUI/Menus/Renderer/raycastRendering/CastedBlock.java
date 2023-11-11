package GUI.Menus.Renderer.raycastRendering;

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
    private long[] blockCords;//[0] = x, [1] = y, [2] = z.

    //The type of block it is.
    private int[][] type; //[0] block id, [1] = face

    //The location on the screen. The cast block is located
    private int[] screenCords; //[0] = x, //[1] = y
    //if the block is shaded
    private boolean isShaded;

    public CastedBlock(int[] screenCords){
        this.screenCords = screenCords;
        this.type = new int[2][2];
    }

    public void setScreenY(int newY){
        this.screenCords[1] = newY;
    }

    //set the cast blocks info
    public void set(long[] newCords){
        this.blockCords = newCords;
    }

    public void setTriangle(int triangle, int[] newType){
        this.type[triangle] = newType;
    }

    public int getScreenX(){
        return screenCords[0];
    }
    public int getScreenY(){
        return screenCords[1];
    }

    //get the cords of the block
    public long[] getBlockCords(){
        return blockCords;
    }
    //get the type of block
    public int[] getType(int triangle){
        return type[triangle];
    }

    public void setShaded(boolean b) {
        this.isShaded = b;
    }

    public boolean isShaded() {
        return this.isShaded;
    }
}
