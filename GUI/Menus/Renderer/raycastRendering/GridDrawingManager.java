package GUI.Menus.Renderer.raycastRendering;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

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
    private static final TextureManager textureManager = new TextureManager();


    //graphics
    Graphics2D graphics = this.createGraphics();


    //Screen center
    private int xCenter;
    private int yCenter;


    //Triangle sizes
    public final int BLOCK_WIDTH_FACTOR = 32;
    public final int BLOCK_HEIGHT_FACTOR = 16;

    //create gameData class
    private final GameData gameData;

    //constructor
    GridDrawingManager(GameData gameData, int xRez, int yRez) {
        super(xRez,yRez, TYPE_4BYTE_ABGR_PRE);
        this.gameData = gameData;

        this.update();
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

    //draws the top 2 faces of a block, with 2 triangles inputs.
    public void drawTopBlock(int x, int y, int[] triangle1, int[] triangle2){
        x = x - y;
        y = x + y * 2;
        drawTriangle(x, y, triangle1[0], triangle1[1]);
        drawTriangle(x + 1, y, triangle2[0], triangle2[1]);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Update
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *Methods for drawing triangles at specific locations
     */

    //updates the center position
    public void update(){
        this.xCenter = gameData.SCREEN_X_REZ/2;
        this.yCenter = gameData.SCREEN_Y_REZ/2;
    }

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
