package GUI.IsoRenderer.Camera;

import GUI.IsoRenderer.Camera.GridManager.Structure.CastedChunk;
import GUI.IsoRenderer.Camera.GridManager.Structure.CastedChunkManager;
import GameData.GameData;

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
        cameraData.camWorldKey = gameData.keyMod.getRelativeKey(cameraData.camWorldKey, 0, 0, 0, 250);

        //set up graphics
        this.graphics = this.getGraphics();
        this.cameraData.castedChunkManager = new CastedChunkManager(gameData, cameraData);
    }

    public void renderFrame(){
        //Draw the words chunks
        drawCastedChunks();


        //Draw UI elements
        //drawUI();
    }

    //Draw the rendered chunks to the screen at their assigned positions.
    public void drawCastedChunks(){
        graphics.setColor(Color.CYAN);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        //loop through Chunk Array
        for (int x = 0; x < cameraData.castedChunkManager.getChunks().length; x++){
            for (int y = 0; y < cameraData.castedChunkManager.getChunks()[0].length; y++){
                //draw the chunks with in the correct position
                CastedChunk castedChunk = cameraData.castedChunkManager.getChunks()[x][y];

                int xDrawCords = ((castedChunk.getScreenX() + cameraData.xCamOffSet) - (cameraData.camXCenterPixel));
                int yDrawCords = ((castedChunk.getScreenY()  + cameraData.yCamOffSet) - (cameraData.camYCenterPixel));

                graphics.drawImage(castedChunk.getRenderedImage(), xDrawCords , yDrawCords, null);
            }
        }
    }
}
