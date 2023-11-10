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
        graphics.drawImage(textureManager.getFaceTexture(5, 0), 35 * 1, 50, null);
        graphics.drawImage(textureManager.getFaceTexture(5, 1), 35 * 2, 50, null);
        graphics.drawImage(textureManager.getFaceTexture(5, 2), 35 * 3, 50, null);
        graphics.drawImage(textureManager.getFaceTexture(5, 3), 35 * 4, 50, null);
        graphics.drawImage(textureManager.getFaceTexture(5, 4), 35 * 5, 50, null);
        graphics.drawImage(textureManager.getFaceTexture(5, 5), 35 * 6, 50, null);
    }

    public void drawShadedTextures(){

        //draw a shaded top face
        BufferedImage face1 = faceShaders.shadeTopFaceTop(textureManager.getFaceTexture(5, 0));
        BufferedImage face2 = faceShaders.shadeTopFaceTop(textureManager.getFaceTexture(5, 3));
        graphics.drawImage(face1, 35, 150,  null);
        graphics.drawImage(face2, 35+32, 150,  null);

        //draw a shaded bot face
        BufferedImage face3 = faceShaders.shadeTopFaceBottom(textureManager.getFaceTexture(5, 0));
        BufferedImage face4 = faceShaders.shadeTopFaceBottom(textureManager.getFaceTexture(5, 3));
        graphics.drawImage(face3, 35, 300,  null);
        graphics.drawImage(face4, 35+32, 300,  null);

    }

}

