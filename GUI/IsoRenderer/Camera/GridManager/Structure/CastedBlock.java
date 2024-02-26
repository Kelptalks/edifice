package GUI.IsoRenderer.Camera.GridManager.Structure;

import GUI.IsoRenderer.Textures.Texture;
import GameData.Block;

import java.util.ArrayList;

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
    //if the block is shaded

    public CastedBlock(int[] screenCords){
        this.screenCords = screenCords;
        blockType[0] = Block.Air;
        blockType[1] = Block.Air;
        texture[0] = Texture.TopLeftFace;
        texture[1] = Texture.TopRightFace;
    }

    private boolean highlight = false;

    public void setHighlight(){
        this.highlight = true;
    }

    public boolean getHighLight(){
        return highlight;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Render location
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * the isometric cords this
     * block falls on
     */

    public int[] getIsoScreenCords(){
        return screenCords;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  worldKey
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    private long worldKey;
    public void setWorldKey(long key) {
        this.worldKey = key;
    }

    public long getWorldKey(){
        return worldKey;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Block Type
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * The type of block each texture
     * is.
     */

    private Block[] blockType = new Block[2];
    public Block[] getBlockType(){
        return blockType;
    }

    public void setBlockType(int side, Block block){
        this.blockType[side] = block;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Texture
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * getting the texture of each
     * for each side of the panel
     */

    private Texture[] texture = new Texture[2];

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

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Filters
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Filter textures for each side
     * of the panel
     */

    private Texture[] filter = new Texture[2];

    public void setLeftFilter(Texture texture) {
        this.filter[0] = texture;
    }

    public void setRightFilter(Texture texture) {
        this.filter[1] = texture;
    }

    public Texture getLeftFilter(){
        return this.filter[0];
    }

    public Texture getRightFilter(){
        return this.filter[1];
    }

    ArrayList<Texture> leftFilters = new ArrayList<Texture>();
    ArrayList<Texture> rightFilters = new ArrayList<Texture>();

    public void addLeftFilter(Texture texture){
        this.leftFilters.add(texture);
    }

    public void addRightFilter(Texture texture){
        this.rightFilters.add(texture);
    }

    public ArrayList<Texture> getLeftFilters(){
        return leftFilters;
    }

    public ArrayList<Texture> getRightFilters(){
        return rightFilters;
    }

    public void clearFilters(){
        rightFilters.clear();
        leftFilters.clear();
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Block Key
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    private long[] blockKey = new long[2];
    public void setLeftBlockKey(long key) {
        this.blockKey[0] = key;
    }

    public void setRightBlockKey(long key) {
        this.blockKey[1] = key;
    }

    public long getLeftBLockKey(){
        return blockKey[0];
    }

    public long getRightBLockKey(){
        return blockKey[1];
    }
}
