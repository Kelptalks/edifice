package GUI.Menus.Renderer.raycastRendering;

import World.DataStorage.Blueprint.Blueprint;
import GameData.GameData;
import World.World;

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
    //private World world = new World();

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
    public void rayCast(){
        for (int y = 0; y < yCamRez; y++){
            for (int x =  0; x < xCamRez; x++) {
                drawingManager.drawTopBlock(culledCoordMods[x][y][0], culledCoordMods[x][y][1],
                        pathLeftTop(culledCoordMods[x][y][0] + GameData.playerXCamCor, culledCoordMods[x][y][1]+ GameData.playerYCamCor),
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
        System.out.println("Mouse clicked at component coordinates: [" + screenX + ", " + screenY + "]");

        world.setBlock(0, 0, 0, 0);
    }

    private long[] pathLeftTopCoords(long x, long y){
        int z = 0;
        int block = world.getBlock(x, y, z);
        for (int distance = 0; distance < drawDistance; distance++)
        {
            x--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                return new long[]{x, y, z};
            }

            y--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                return new long[]{x, y, z};
            }

            z--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                return new long[]{x, y, z};
            }
        }
        return new long[]{x, y, z};
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Cam Control
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */

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
        this.culledCoordMods = CoordMods;
    }

    //draw distance
    private int drawDistance = 500;

    //Render Size
    private int xCamRez = 29;
    private int yCamRez = 61;


}
