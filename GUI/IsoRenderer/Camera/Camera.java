package GUI.IsoRenderer.Camera;

import GUI.IsoRenderer.Camera.GridManager.GridManager;
import GUI.IsoRenderer.Camera.GridManager.Structure.CastedChunk;
import GUI.IsoRenderer.Camera.GridManager.Structure.CastedChunkCuller;
import GUI.IsoRenderer.Camera.RayCaster.RayCaster;
import GUI.IsoRenderer.Textures.Texture;
import GameData.GameData;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Camera extends BufferedImage {
    GameData gameData;
    CameraData cameraData;
    private CastedChunk[][] castedChunks;
    private RayCaster rayCaster;
    private CastedChunkCuller castedChunkCuller;
    Graphics graphics;
    public Camera(GameData gameData, CameraData cameraData){
        super(cameraData.camXRez, cameraData.camYRez, TYPE_4BYTE_ABGR_PRE);
        this.gameData = gameData;
        this.cameraData = cameraData;
        //set up graphics
        this.graphics = this.getGraphics();
        //create raycasting tool
        this.rayCaster = new RayCaster(gameData);


        this.castedChunkCuller = new CastedChunkCuller(gameData, cameraData);

        //create chunk pool
        this.castedChunks = castedChunkCuller.getCulledCordMods();
        castedChunkCuller.setCulledCordWorldKeys(castedChunks);
        renderChunks(castedChunks);



    }

    public void renderFrame(){
        drawCastedChunks();
        Texture[] allTextures = Texture.values();


        for (int x = 0; x < allTextures.length; x++){
            this.graphics.drawImage(cameraData.textureManager.getFilter(0, allTextures[x]), 64, 50 *x, null);
        }
        //this.graphics.drawImage(cameraData.textureManager.getFilter(0, Texture.BotBotHalf), 64, 50, null);
    }

    public void drawCastedChunks(){
        for (int x = 0; x < castedChunks.length; x++){
            for (int y = 0; y < castedChunks[0].length; y++){
                CastedChunk castedChunk = castedChunks[x][y];
                castedChunk.getScreenX();
                graphics.drawImage(castedChunk.getRenderedImage(), castedChunk.getScreenX() + cameraData.xCamOffSet, castedChunk.getScreenY()  + cameraData.yCamOffSet, null);
            }
        }
    }

    public void shiftChunkArray(int xShift, int yShift){

    }

    public void renderChunks(CastedChunk[][] castedChunks){
        GridManager gridManager = new GridManager();
        for (int x = 0; x < castedChunks.length; x++){
            for (int y = 0; y < castedChunks[0].length; y++) {
                CastedChunk castedChunk = castedChunks[x][y];
                renderChunk(castedChunk);
                castedChunk.setScreenCords(gridManager.isoToScreen(castedChunk.getIsoCords(), cameraData.yChunkPixelRez));
            }
        }
    }
    public void renderChunk(CastedChunk castedChunk){
        rayCaster.castBlocks(castedChunk.getCastedBlocks());
        castedChunk.renderChunks();
    }
}
