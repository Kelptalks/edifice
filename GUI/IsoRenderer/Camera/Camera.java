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
    private RayCaster rayCaster;
    Graphics graphics;
    public Camera(GameData gameData, CameraData cameraData){
        super(cameraData.camXRez, cameraData.camYRez, TYPE_4BYTE_ABGR_PRE);
        this.gameData = gameData;
        this.cameraData = cameraData;
        //set up graphics
        this.graphics = this.getGraphics();
        //create raycasting tool
        this.rayCaster = new RayCaster(gameData);


        this.cameraData.castedChunkCuller = new CastedChunkCuller(gameData, cameraData);
        this.cameraData.castedChunks = cameraData.castedChunkCuller.getCulledCordMods();

        //SetUpChunks
        cameraData.castedChunkCuller.setCulledCordWorldKeys(cameraData.castedChunks);

        //Render Chunks
        renderChunks(cameraData.castedChunks);



    }

    public void renderFrame(){
        drawCastedChunks();
        Texture[] allTextures = Texture.values();


        for (int x = 0; x < allTextures.length; x++){
            this.graphics.drawRect(64, 32 * x, 32, 32);
            this.graphics.drawImage(cameraData.textureManager.getFilter(0, allTextures[x]), 64, 32 *x, null);
        }
        //this.graphics.drawImage(cameraData.textureManager.getFilter(0, Texture.BotBotHalf), 64, 50, null);
    }

    public void drawCastedChunks(){
        for (int x = 0; x < cameraData.castedChunks.length; x++){
            for (int y = 0; y < cameraData.castedChunks[0].length; y++){
                CastedChunk castedChunk = cameraData.castedChunks[x][y];


                int scale = 1;
                int xDrawCords = ((castedChunk.getScreenX() + cameraData.xCamOffSet) / scale) - (cameraData.camXCenterPixel/scale);
                int yDrawCords = ((castedChunk.getScreenY()  + cameraData.yCamOffSet) / scale) - (cameraData.camYCenterPixel/scale);


                graphics.drawImage(castedChunk.getRenderedImage(), xDrawCords , yDrawCords, null);
            }
        }
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

        castedChunk.renderChunk();
    }
}
