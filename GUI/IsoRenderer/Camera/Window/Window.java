package GUI.IsoRenderer.Camera.Window;

import GUI.IsoRenderer.Camera.CameraData;
import GUI.IsoRenderer.Textures.Texture;
import GameData.Block;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Window extends BufferedImage {

    private Graphics graphics;
    private CameraData cameraData;
    public Window(CameraData cameraData) {
        super(1280, 720, TYPE_4BYTE_ABGR_PRE);
        this.graphics = this.getGraphics();
        this.cameraData = cameraData;

        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        drawTopBar();
        drawBlockGrid(10, 10, 0, 20);
    }

    public void renderUi(){

    }

    public void drawTopBar(){
        graphics.setColor(Color.GRAY);
        graphics.fillRect(0, 0, this.getWidth(), 20);
    }

    public void drawBlockGrid(int xScale, int yScale, int xCor, int yCor){
        graphics.setColor(Color.BLACK);
        for (int y = 0; y < yScale; y++){
            for (int x = 0; x < xScale; x++){
                graphics.drawRect(x * 80 + xCor, y * 80 + yCor, 80, 80);
                drawBlock(Block.Stone, (x * 80) + 5 + xCor, (y * 80) + 5 + yCor);
            }
        }
    }

    public void drawBlock(Block block, int x, int y){
        this.graphics.drawImage(cameraData.textureManager.getTexture(block, Texture.TopLeftFace), x, y, null);
        this.graphics.drawImage(cameraData.textureManager.getTexture(block, Texture.LeftTopFace), x, y + 16, null);
        this.graphics.drawImage(cameraData.textureManager.getTexture(block, Texture.LeftBotFace), x, y + 32, null);

        this.graphics.drawImage(cameraData.textureManager.getTexture(block, Texture.TopRightFace), x + 32, y, null);
        this.graphics.drawImage(cameraData.textureManager.getTexture(block, Texture.RightTopFace), x + 32, y + 16, null);
        this.graphics.drawImage(cameraData.textureManager.getTexture(block, Texture.RightBotFace), x + 32, y + 32, null);
    }
}
