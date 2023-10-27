package GUI.Menus.Renderer.raycastRendering;

import World.DataStorage.Blueprint.Blueprint;
import GameData.GameData;

import java.awt.*;
import java.awt.image.BufferedImage;
/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Block manipulation
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 */
public class RaycastRenderer extends BufferedImage {

    private GridDrawingManager drawingManager;
    private Graphics graphics = this.getGraphics();
    private Blueprint world = new Blueprint(1000, 1000, 1000);

    private int[][][] culledCoordMods;

    public RaycastRenderer(GameData gameData) {
        super(gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ, TYPE_4BYTE_ABGR_PRE);
        this.drawingManager = new GridDrawingManager(gameData ,gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ);
        updateCulledCoordMods();

        rayCastWithCulling();

    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Ray casting
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */
    public void rayCast(){

        //cycle through all blocks on a flat plane

        //I need to fix, so I get blocks that would fill the screen,
        for (int y = 0; y < yCamRez; y++){
           for (int x =  0; x < xCamRez; x++){
               //This draws a block top based off 2 triangles inputs
               drawingManager.drawTopBlock(x, y, pathLeftTop(x + GameData.playerXCamCor, y+ GameData.playerYCamCor),
                        pathRightTop(x + GameData.playerXCamCor, y+ GameData.playerYCamCor));
           }
        }
        graphics.drawImage(drawingManager, 0, 0, null);
    }

    public void rayCastWithCulling(){
        for (int y = 0; y < yCamRez; y++){
            for (int x =  0; x < xCamRez; x++) {
                drawingManager.drawTopBlock(culledCoordMods[x][y][0], culledCoordMods[x][y][1], pathLeftTop(culledCoordMods[x][y][0] + GameData.playerXCamCor, culledCoordMods[x][y][1]+ GameData.playerYCamCor),
                        pathRightTop(x + GameData.playerXCamCor, y+ GameData.playerYCamCor));
            }
        }
        graphics.drawImage(drawingManager, 0, 0, null);
    }

    //separate casting from drawing to enable threading.
    private void drawCast(){

    }

    private int[] pathLeftTop(long x, long y){
        int z = 0;
        int block = world.getBlock(x, y, z);
        for (int distance = 0; distance < drawDistance; distance++)
        {
            x--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                return new int[]{block, 4};
            }

            y--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                return new int[]{block, 2};
            }

            z--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                return new int[]{block, 0};
            }
        }
        return new int[]{1, 0};
    }

    private int[] pathRightTop(long x, long y){
        int z = 0;
        int block = world.getBlock(x, y, z);
        for (int distance = 0; distance < drawDistance; distance++)
        {
            y--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                return new int[]{block, 1};
            }

            x--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                return new int[]{block, 5};
            }

            z--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                return new int[]{block, 3};
            }
        }
        return new int[]{1, 3};
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Editing blocks
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */

    //take in the screen cords and return block cords to be modified
    public void pathModBlock(int screenX, int screenY){
        //convert screen cords to block cords
    }

    public void updateCulledCoordMods(){
        int[][][] CoordMods = new int[xCamRez][yCamRez][2];

        //cycle through cam cords
        for (int y = 0; y < yCamRez; y++){
            //used to each start
            int[] rowOrginMod = new int[]{y, y};
            for (int x = 0; x < xCamRez; x++) {
                //set the cords to the modded cords.
                CoordMods[x][y] = new int[]{rowOrginMod[0] + x, rowOrginMod[1] - x};

                //for the first row the conversion would start at (0,0) and mod (x+1, y-1)
                //for row conversion would need to be x+1, y+1

                //I need to get the relative block cords that would be on camera
                /* (0+0, 0+0), (0+1, 0-1), (1+1, -1-1)
                 * (0+1, 0+1),
                 *
                 *
                 *
                 *
                 */
            }
        }
        this.culledCoordMods = CoordMods;
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Cam Control
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */

    //draw distance
    private int drawDistance = 500;

    //Render Size
    private int xCamRez = 10;
    private int yCamRez = 10;
}
