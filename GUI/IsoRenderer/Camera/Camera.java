package GUI.IsoRenderer.Camera;

import GUI.IsoRenderer.Camera.GridManager.Structure.CastedBlock;
import GUI.IsoRenderer.Camera.GridManager.Structure.CastedChunk;
import GUI.IsoRenderer.Camera.GridManager.Structure.CastedChunkManager;
import GUI.IsoRenderer.Camera.Window.Window;
import GUI.IsoRenderer.Textures.Texture;
import GameData.GameData;
import GameData.Block;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Camera extends BufferedImage {
    GameData gameData;
    CameraData cameraData;
    Graphics graphics;
    public Camera(GameData gameData, CameraData cameraData){
        super(cameraData.camXRez, cameraData.camYRez, TYPE_4BYTE_ABGR_PRE);
        this.gameData = gameData;
        this.cameraData = cameraData;

        //set up graphics
        this.graphics = this.getGraphics();
        this.cameraData.castedChunkManager = new CastedChunkManager(gameData, cameraData);
        this.cameraData.window = new Window(cameraData);
    }

    public void renderFrame(){
        //Draw the words chunks
        drawCastedChunks();

        //Draw UI elements
        //drawUI();
    }

    public void drawUI(){
        cameraData.window.renderUi();
        graphics.drawImage(cameraData.window, 0, 0, null);
    }

    //Draw the rendered chunks to the screen at their assigned positions.
    public void drawCastedChunks(){
        //loop through Chunk Array
        for (int x = 0; x < cameraData.castedChunkManager.getChunks().length; x++){
            for (int y = 0; y < cameraData.castedChunkManager.getChunks()[0].length; y++){
                //draw the chunks with in the correct position
                CastedChunk castedChunk = cameraData.castedChunkManager.getChunks()[x][y];

                int xDrawCords = (castedChunk.getScreenX() + cameraData.xCamOffSet) - (cameraData.camXCenterPixel);
                int yDrawCords = (castedChunk.getScreenY()  + cameraData.yCamOffSet) - (cameraData.camYCenterPixel);
                graphics.drawImage(castedChunk.getRenderedImage(), xDrawCords , yDrawCords, null);
            }
        }
    }


}
