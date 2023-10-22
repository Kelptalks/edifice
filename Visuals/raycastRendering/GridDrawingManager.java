package Visuals.raycastRendering;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Constants.Constants;
import Visuals.Textures.TextureManager;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Grid Manager class
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*This class is responsable for drawing specific textures to the grid
*based off there face type.
*
*/

public class GridDrawingManager extends BufferedImage {
    private static final TextureManager textureManager = new TextureManager();
    Graphics2D graphics = this.createGraphics();

    GridDrawingManager(int xRez, int yRez) {
        super(xRez,yRez, TYPE_4BYTE_ABGR_PRE);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Drawing methods
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *Methods for drawing triangles at specific locations
     */

    //Draws a triangle in a specific cord in the triangle grid
    public void drawTriangle(int x, int y, int blockType, int triangle) {
        graphics.drawImage(textureManager.getFaceTexture(blockType, triangle), x * Constants.BLOCK_WIDTH_FACTOR, y * Constants.BLOCK_HEIGHT_FACTOR, null);
    }

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
                g2d.drawImage(textureManager.getFaceTexture(1, 1), x * 32, y * 32 + offset, null);
            }
        }
    }
}
