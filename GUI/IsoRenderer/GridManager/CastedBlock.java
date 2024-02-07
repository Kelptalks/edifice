package GUI.IsoRenderer.GridManager;

import GUI.IsoRenderer.Textures.Texture;
import GameData.Block;

public class CastedBlock {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  CastedBlock
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * A CastedBlock block is a class
    * to store info about a block's * rendered details from the at
    * a screen location. Used for
    * organizing Rendered blocks for
    * cashing, and adding shadows
    */

    private int[] screenCords; //[0] = x, //[1] = y
    private Block[] blockType = new Block[2];
    private Texture[] texture = new Texture[2];

    private long worldKey;
    //if the block is shaded

    public CastedBlock(int[] screenCords){
        this.screenCords = screenCords;
        blockType[0] = Block.Air;
        blockType[1] = Block.Air;
        texture[0] = Texture.TopLeftFace;
        texture[1] = Texture.TopRightFace;
    }

    public int[] getIsoScreenCords(){
        return screenCords;
    }


    public void setWorldKey(long key) {
        this.worldKey = key;
    }

    public long getWorldKey(){
        return worldKey;
    }

    public Block[] getBlockType(){
        return blockType;
    }

    public void setBlockType(int side, Block block){
        this.blockType[side] = block;
    }

    public void setLeftTexture(Texture texture) {
        this.texture[0] = texture;
    }

    public void setRightTexture(Texture texture) {
        this.texture[1] = texture;
    }

    public Texture getLeftTexture(){
        return this.texture[0];
    }

    public Texture getRightTexture(){
        return this.texture[1];
    }
}
