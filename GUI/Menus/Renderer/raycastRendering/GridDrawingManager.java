package GUI.Menus.Renderer.raycastRendering;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import GUI.Menus.Renderer.Textures.FaceShaders;
import GUI.Menus.Renderer.Textures.TextureManager;
import GameData.GameData;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Grid Manager class
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*This class is responsable for drawing specific textures to the grid
*based off there face type.
*
*/

public class GridDrawingManager extends BufferedImage {

    //Textures
    private final TextureManager textureManager = new TextureManager();
    private final FaceShaders faceShader = new FaceShaders();

    //graphics
    Graphics2D graphics = this.createGraphics();

    //Triangle sizes
    public final int BLOCK_WIDTH_FACTOR = 32;
    public final int BLOCK_HEIGHT_FACTOR = 16;

    //create gameData class
    private final GameData gameData;

    //constructor
    GridDrawingManager(GameData gameData, int xRez, int yRez) {
        super(xRez,yRez, TYPE_4BYTE_ABGR_PRE);
        graphics.drawImage(textureManager.getFaceTexture(1, 3), 20, 1000, null);
        BufferedImage shadedFull = faceShader.shadeTopFaceTop(textureManager.getFaceTexture(1, 3));
        graphics.drawImage(shadedFull, 50, 1000, null);
        this.gameData = gameData;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Drawing methods
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *Methods for drawing triangles at specific locations
     */

    //Draws a triangle in a specific cord in the triangle grid
    private void drawTriangle(int x, int y, int blockType, int triangle) {
        graphics.drawImage(textureManager.getFaceTexture(blockType, triangle), (x * BLOCK_WIDTH_FACTOR), (y * BLOCK_HEIGHT_FACTOR), null);

    }
    private void drawShadedTriangle(int x, int y, int blockType, int triangle) {
        graphics.drawImage(faceShader.shadeWhole(textureManager.getFaceTexture(blockType, triangle)), (x * BLOCK_WIDTH_FACTOR), (y * BLOCK_HEIGHT_FACTOR), null);
    }

    //draws the top 2 faces of a block, with 2 triangles inputs.
    public void drawTopBlock(CastedBlock castedBlock){
        int x = castedBlock.getScreenX();
        int y = castedBlock.getScreenY();
        x = x - y;
        y = x + y * 2;
        if (castedBlock.isShaded()){
            drawShadedTriangle(x, y, castedBlock.getType(0)[0], castedBlock.getType(0)[1]);
            drawShadedTriangle(x + 1, y, castedBlock.getType(1)[0], castedBlock.getType(1)[1]);
        }
        else {
            drawTriangle(x, y, castedBlock.getType(0)[0], castedBlock.getType(0)[1]);
            drawTriangle(x + 1, y, castedBlock.getType(1)[0], castedBlock.getType(1)[1]);
        }
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Update
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *Methods for drawing triangles at specific locations
     */

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Debugging
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *Methods for drawing triangles at specific locations
     */

    //draws a block at a cord
    public void drawBlock(int x, int y, int blockType) {
        x = x - y;
        y = x + y * 2;
        drawTriangle(x, y, blockType, 0);
        drawTriangle(x, y + 1, blockType, 1);
        drawTriangle(x, y + 2, blockType, 2);
        drawTriangle(x + 1, y, blockType, 3);
        drawTriangle(x + 1, y + 1, blockType, 4);
        drawTriangle(x + 1, y + 2, blockType, 5);
    }

    //draws a grid using triangles
    public void testDrawTriangleGrid() {
        Graphics2D g2d = this.createGraphics();
        g2d.setColor(Color.BLACK);

        for (int x = 0; x < 60; x++) {
            g2d.drawLine(x * 32, 0, x * 32, 1080);

            int offset = 0;
            if (x % 2 == 0) {
                offset = 16;
            } else {
                offset = 0;
            }
            for (int y = 0; y < 34; y++) {
                g2d.drawImage(textureManager.getFaceTexture(1, 1), (x * 32), (y * 32) + offset, null);
            }
        }
    }
}
