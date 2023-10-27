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

    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Ray casting
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */
    public void rayCast2(){

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

    public void rayCast(){
        for (int y = 0; y < yCamRez; y++){
            for (int x =  0; x < xCamRez; x++) {
                drawingManager.drawTopBlock(culledCoordMods[x][y][0], culledCoordMods[x][y][1], pathLeftTop(culledCoordMods[x][y][0] + GameData.playerXCamCor, culledCoordMods[x][y][1]+ GameData.playerYCamCor),
                        pathRightTop(culledCoordMods[x][y][0] + GameData.playerXCamCor, culledCoordMods[x][y][1]+ GameData.playerYCamCor));
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
            int[] rowOrginMod = new int[]{y/2, y/2};
            if (y%2 != 0){
                rowOrginMod[0]++;
            }
            for (int x = 0; x < xCamRez; x++){
                CoordMods[x][y] = new int[]{rowOrginMod[0] + x, rowOrginMod[1] - x};
            }
        }
        //this is the kind of shit I need it to be
        if (CoordMods[0][0][0] != 0){
            System.out.println("fail");
            System.out.println(CoordMods[0][0][0]);
        }
        CoordMods[0][0] = new int[]{0, 0};
        CoordMods[1][0] = new int[]{1, -1};
        CoordMods[2][0] = new int[]{2, -2};

        CoordMods[0][1] = new int[]{1, 0};
        CoordMods[1][1] = new int[]{2, -1};
        CoordMods[2][1] = new int[]{3, -2};

        CoordMods[0][2] = new int[]{1, 1};
        CoordMods[1][2] = new int[]{2, 0};
        CoordMods[2][2] = new int[]{3, -1};
        if (CoordMods[1][3][0] != 3){
            System.out.println("fail");
            System.out.println(CoordMods[0][0][0]);
        }
        CoordMods[0][3] = new int[]{2, 1};
        CoordMods[1][3] = new int[]{3, 0};
        CoordMods[2][3] = new int[]{4, -1};

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
    private int xCamRez = 25;
    private int yCamRez = 50;
}
