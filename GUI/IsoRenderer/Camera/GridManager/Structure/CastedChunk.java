package GUI.IsoRenderer.Camera.GridManager.Structure;

import GUI.IsoRenderer.Camera.CameraData;
import GUI.IsoRenderer.Textures.Texture;
import GameData.Block;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_4BYTE_ABGR_PRE;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Casted Chunk
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * A casted chunk is a small collection
 * of casted blocks used to cash
 * and
 */
public class CastedChunk {
    //The collection of casted blocks that make up this chunk
    private CastedBlock[][] castedBlocks;
    private CameraData cameraData;
    private Image renderedImage;
    private long worldKey;

    public CastedChunk(CameraData cameraData){
        this.cameraData = cameraData;
        this.castedBlocks = cameraData.castedBlockCuller.getIsoCastedBlock(cameraData.xChunkRez, cameraData.yChunkRez);
    }

    public void setRenderedImage(Image renderedImage){
        this.renderedImage = renderedImage;
    }

    public CastedBlock[][] getCastedBlocks(){
        return this.castedBlocks;
    }

    public void updateCastedBlocks(){
        this.castedBlocks = cameraData.castedBlockCuller.getIsoCastedBlock(cameraData.xChunkRez, cameraData.yChunkRez);
    }

    public void renderChunk() {
        //rayCast the blocks in the chunk
        cameraData.renderer.renderChunk(this);

    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Getters/Setters
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */

    //Image
    public Image getRenderedImage() {
        return renderedImage;
    }

    //World key
    public void setWorldKey(long newWorldKey){
        this.worldKey = newWorldKey;
    }
    public long getWorldKey(){
        return worldKey;
    }

    //Iso cords
    private int[] isoCords = new int[2];
    public void setIsoCords(int[] newCords) {
        isoCords = newCords;
    }
    public int[] getIsoCords(){
        return isoCords;
    }

    //Screen cords
    private int[] screenCor = new int[2];
    public void setScreenCords(int[] cords){
        this.screenCor = cords;
    }
    public int getScreenX() {
        return screenCor[0];
    }

    public int getScreenY() {
        return screenCor[1];
    }
}
