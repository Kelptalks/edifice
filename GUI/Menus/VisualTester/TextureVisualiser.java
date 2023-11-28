package GUI.Menus.VisualTester;

import GUI.Menus.Renderer.Textures.FaceShaders;
import GUI.Menus.Renderer.Textures.TextureManager;
import GameData.GameData;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TextureVisualiser extends BufferedImage {
    private Graphics2D graphics = (Graphics2D) this.getGraphics();

    private TextureManager textureManager;
    private FaceShaders faceShaders;
    public TextureVisualiser(GameData gameData) {
        super(gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ, TYPE_4BYTE_ABGR_PRE);
        this.textureManager = new TextureManager();
        this.faceShaders = new FaceShaders();
        drawTextures();
        drawShadedTextures();
    }

    public void drawTextures(){
        graphics.drawImage(textureManager.getFaceTexture(4, 0), 35 * 1, 50, null);
        graphics.drawImage(textureManager.getFaceTexture(4, 1), 35 * 2, 50, null);
        graphics.drawImage(textureManager.getFaceTexture(4, 2), 35 * 3, 50, null);
        graphics.drawImage(textureManager.getFaceTexture(4, 3), 35 * 4, 50, null);
        graphics.drawImage(textureManager.getFaceTexture(4, 4), 35 * 5, 50, null);
        graphics.drawImage(textureManager.getFaceTexture(4, 5), 35 * 6, 50, null);
    }

    public void drawShadedTextures(){
        int y = 50;
        //shaded top face left
        y += 50;
        graphics.drawImage(textureManager.getFaceTexture(4, 2), 32, y, null);
        graphics.drawImage(faceShaders.getShader(0, 1), 32, 100, null);
        //shaded bot face left
        y += 50;
        graphics.drawImage(textureManager.getFaceTexture(4, 2), 32, y, null);
        graphics.drawImage(faceShaders.getShader(0, 2), 32, 150, null);

        //shaded top face right
        y += 50;
        graphics.drawImage(textureManager.getFaceTexture(4, 5), 32, y, null);
        graphics.drawImage(faceShaders.getShader(1, 1), 32, 200, null);
        //shaded bot face right
        y += 50;
        graphics.drawImage(textureManager.getFaceTexture(4, 5), 32, y, null);
        graphics.drawImage(faceShaders.getShader(1, 2), 32, 250, null);


        //side
        y += 50;
        int x = 25;
        y += 50;
        graphics.drawImage(textureManager.getFaceTexture(4, 1), x, y+16, null);
        graphics.drawImage(textureManager.getFaceTexture(4, 4), x, y, null);
        graphics.drawImage(faceShaders.getShader(0, 3), x , y, null);

        y += 50;
        graphics.drawImage(textureManager.getFaceTexture(4, 1), x, y+16, null);
        graphics.drawImage(textureManager.getFaceTexture(4, 4), x, y, null);
        graphics.drawImage(faceShaders.getShader(0, 4), x, y, null);

        y += 50;
        graphics.drawImage(textureManager.getFaceTexture(4, 1), x, y+16, null);
        graphics.drawImage(textureManager.getFaceTexture(4, 4), x, y, null);
        graphics.drawImage(faceShaders.getShader(1, 3), x, y, null);

        y += 50;
        graphics.drawImage(textureManager.getFaceTexture(4, 1), x, y+16, null);
        graphics.drawImage(textureManager.getFaceTexture(4, 4), x, y, null);
        graphics.drawImage(faceShaders.getShader(1, 4), x, y, null);

        //whole

        y += 50;
        graphics.drawImage(textureManager.getFaceTexture(4, 2), 32, y, null);
        graphics.drawImage(faceShaders.getShader(0, 5), 32, y, null);

        //shaded bot face left
        y += 50;
        graphics.drawImage(textureManager.getFaceTexture(4, 5), 32, y, null);
        graphics.drawImage(faceShaders.getShader(1, 6), 32, y, null);

        y += 50;
        graphics.drawImage(textureManager.getFaceTexture(4, 1), x, y+16, null);
        graphics.drawImage(textureManager.getFaceTexture(4, 4), x, y, null);
        graphics.drawImage(faceShaders.getShader(1, 7), x, y, null);

        y += 50;
        graphics.drawImage(textureManager.getFaceTexture(4, 1), x, y+16, null);
        graphics.drawImage(textureManager.getFaceTexture(4, 4), x, y, null);
        graphics.drawImage(faceShaders.getShader(1, 8), x, y, null);


    }

}

