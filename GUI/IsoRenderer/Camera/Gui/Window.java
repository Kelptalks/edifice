package GUI.IsoRenderer.Camera.Gui;

import GUI.IsoRenderer.Camera.CameraData;
import GUI.IsoRenderer.Textures.Texture;
import GameData.Block;
import GameData.GameData;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Window extends BufferedImage {
    private Graphics graphics;
    private CameraData cameraData;
    private GameData gameData;

    public Window(GameData gameData, CameraData cameraData) {
        super(1280, 720, TYPE_4BYTE_ABGR_PRE);
        this.graphics = this.getGraphics();
        this.cameraData = cameraData;
        this.gameData = gameData;

        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        drawTopBar();
        drawBlockGrid(0, 20);
    }


    //Cords for the location the gui is rendered
    private int xScreenCor = 100;
    public int getXScreenCor(){
        return xScreenCor;
    }
    private int yScreenCor = 100;
    public int getYScreenCor(){
        return yScreenCor;
    }

    //Bool for determining if the window gets rendered
    private boolean ifVisible = false;
    public boolean isVisible() {
        return ifVisible;
    }
    public void toggleVisible(){
        this.ifVisible = !this.ifVisible;
    }

    public void renderUi(){

    }

    public void drawTopBar(){
        graphics.setColor(Color.GRAY);
        graphics.fillRect(0, 0, this.getWidth(), 20);
    }


    Block[] Blocks = new Block[5];

    public void drawBlockGrid(int xCor, int yCor){
        graphics.setColor(Color.BLACK);
        for (int y = 0; y < gameData.blocks.length; y++){
            graphics.drawRect(xCor, y * 80 + yCor, 80, 80);
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

    public boolean mouseOnWindow(int x, int y) {
        //Check if a window is visible
        if(this.ifVisible) {
            //check if cords
            if (x > xScreenCor && y > yScreenCor) {
                if ((x < xScreenCor + this.getWidth()) && (y < yScreenCor + this.getHeight())){
                    return true;
                }
            }
        }
        return false;
    }

    public void mousePressed(int x, int y) {

    }
}
