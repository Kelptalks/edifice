package GUI.IsoRenderer.Camera.RayCaster;

import GUI.IsoRenderer.Camera.CameraData;
import GUI.IsoRenderer.Camera.GridManager.Structure.CastedBlock;
import GUI.IsoRenderer.Camera.GridManager.Structure.CastedChunk;
import GUI.IsoRenderer.Textures.Texture;
import GameData.GameData;
import GameData.Block;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

import static java.awt.image.BufferedImage.TYPE_4BYTE_ABGR_PRE;

public class Renderer {
    RayCaster rayCaster;
    private CameraData cameraData;
    private GameData gameData;
    ExecutorService executor = Executors.newFixedThreadPool(4);
    public Renderer(GameData gameData, CameraData cameraData){
        this.cameraData = cameraData;
        this.gameData = gameData;
        this.rayCaster = new RayCaster(gameData, cameraData);
    }

    //Render a chunk
    public void renderChunk(CastedChunk castedChunk){
        AtomicReference<Image> temp;
        executor.submit(() -> {
            rayCaster.castChunk(castedChunk);
            drawChunk(castedChunk);
        });
    }

    public void drawChunk(CastedChunk castedChunk){
        Image renderedImage = new BufferedImage(cameraData.xChunkPixelRez, cameraData.yChunkPixelRez, TYPE_4BYTE_ABGR_PRE);
        //draw the ray casted chunk to screen
        Graphics g = renderedImage.getGraphics();
        g.translate((cameraData.xChunkPixelRez)/2, 0);
        //cameraData.castedBlockCuller.setCulledCordWorldKeys(castedBlocks, worldKey);
        for (CastedBlock[] castedBlockRow : castedChunk.getCastedBlocks()){
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
        castedChunk.setRenderedImage(renderedImage);
    }
}
