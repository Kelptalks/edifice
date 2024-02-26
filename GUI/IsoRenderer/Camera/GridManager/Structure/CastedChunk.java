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
    private final CastedBlock[][] castedBlocks;
    private CameraData cameraData;
    private Image renderedImage;
    private long worldKey;

    public CastedChunk(CameraData cameraData){
        this.cameraData = cameraData;
        this.castedBlocks = cameraData.castedBlockCuller.getIsoCastedBlock(cameraData.xChunkRez, cameraData.yChunkRez);
        this.renderedImage = new BufferedImage(cameraData.xChunkPixelRez, cameraData.yChunkPixelRez, TYPE_4BYTE_ABGR_PRE);
    }

    public CastedBlock[][] getCastedBlocks(){
        return this.castedBlocks;
    }

    public void renderChunk() {
        //rayCast the blocks in the chunk
        cameraData.rayCaster.castChunkThread(this);
        //draw the ray casted chunk to screen
        Graphics g = renderedImage.getGraphics();
        g.translate((cameraData.xChunkPixelRez)/2, 0);
        //cameraData.castedBlockCuller.setCulledCordWorldKeys(castedBlocks, worldKey);
        for (CastedBlock[] castedBlockRow : castedBlocks){
            for (CastedBlock castedBlock : castedBlockRow){
                Block[] blockType = castedBlock.getBlockType();
                int[] drawCords = cameraData.gridManager.isoToScreen(castedBlock.getIsoScreenCords());

                Image leftTri = cameraData.textureManager.getTexture(blockType[0], castedBlock.getLeftTexture());
                Image rightTri = cameraData.textureManager.getTexture(blockType[1], castedBlock.getRightTexture());

                g.drawImage(leftTri, drawCords[0], drawCords[1], null);
                g.drawImage(rightTri, drawCords[0] + 32, drawCords[1], null);

                if (castedBlock.getLeftFilter() != null){
                    g.drawImage(cameraData.textureManager.getFilter(0, castedBlock.getLeftFilter()), drawCords[0], drawCords[1], null);
                }
                if (castedBlock.getRightFilter() != null){
                    g.drawImage(cameraData.textureManager.getFilter(0, castedBlock.getRightFilter()), drawCords[0] + 32, drawCords[1], null);
                }

                for (Texture texture : castedBlock.getLeftFilters()){
                    g.drawImage(cameraData.textureManager.getFilter(1, texture), drawCords[0], drawCords[1], null);
                }
                for (Texture texture : castedBlock.getRightFilters()){
                    g.drawImage(cameraData.textureManager.getFilter(1, texture), drawCords[0], drawCords[1] + 32, null);
                }
            }
        }
        g.dispose();
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
