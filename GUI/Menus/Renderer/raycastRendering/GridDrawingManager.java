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
        this.gameData = gameData;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Drawing methods
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *Methods for drawing triangles at specific locations
     */

    //Draws a triangle in a specific cord in the triangle grid
    private void drawTriangle(int x, int y, int blockType, int triangle) {
        graphics.drawImage(textureManager.getFaceTexture(blockType, triangle), x, y, null);
    }

    //draws the top face of the block, using 2 triangle textures
    public void drawCastedBlock(CastedBlock castedBlock){

        //get the cords
        int[] cords = blockCorToScreenCor(castedBlock.getScreenCords());

        //texture drawing | 32 needs to be added shift second texture over
        drawTriangle(cords[0], cords[1], castedBlock.getTriangleTexture(0)[0], castedBlock.getTriangleTexture(0)[1]);
        drawTriangle(cords[0] + 32, cords[1], castedBlock.getTriangleTexture(1)[0], castedBlock.getTriangleTexture(1)[1]);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Coordinate tools
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Methods for converting between isometric and normal cords
     */

    //convert a blocks (x, y) to a screen based (x, y)
    private int[] blockCorToScreenCor(int[] cor){
        //isometric to normal cord conversion
        int x = (cor[0] - cor[1]);
        int y = (x + cor[1]* 2);

        //Block texture size
        x = x * BLOCK_WIDTH_FACTOR;
        y = y * BLOCK_HEIGHT_FACTOR;

        return new int[]{(x), (y)};
    }
}
