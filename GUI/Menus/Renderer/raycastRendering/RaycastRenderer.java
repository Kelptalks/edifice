package GUI.Menus.Renderer.raycastRendering;

import World.DataStorage.Blueprint.Blueprint;
import GameData.GameData;
import World.DataStorage.Octree.ActiveBranch;
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

    private ActiveBranch world = new ActiveBranch();
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
        graphics.setColor(Color.BLACK);
        graphics.fillOval(((xCamRez) * 32)-5, ((xCamRez) * 16)+25, 10, 10);
    }

    //separate casting from drawing to enable threading.
    private void drawCast(){

    }

    private int[] pathLeftTop(long x, long y){
        long z = GameData.playerZCamCor;
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
        return new int[]{0, 0};
    }

    private int[] pathRightTop(long x, long y){
        long z = GameData.playerZCamCor;
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
        return new int[]{0, 3};
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Editing blocks
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */

    public void pathAndPlace(int placingBlock){

        long x = culledCoordMods[xCamRez/2][yCamRez/2][0] + GameData.playerXCamCor;
        long y = culledCoordMods[xCamRez/2][yCamRez/2][1] + GameData.playerYCamCor;

        long z = GameData.playerZCamCor;

        System.out.println(x);
        System.out.println(y);
        int block = world.getBlock(x, y, z);
        for (int distance = 0; distance < drawDistance; distance++)
        {
            y--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                world.setBlock(x, y + 1, z, placingBlock);
                break;
            }

            x--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                world.setBlock(x + 1, y, z, placingBlock);
                break;
            }

            z--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                world.setBlock(x, y, z + 1, placingBlock);
                break;
            }
        }
    }

    public void pathAndRemove(){

        long x = culledCoordMods[xCamRez/2][yCamRez/2][0] + GameData.playerXCamCor;
        long y = culledCoordMods[xCamRez/2][yCamRez/2][1] + GameData.playerYCamCor;

        long z = GameData.playerZCamCor;

        System.out.println(x);
        System.out.println(y);
        int block = world.getBlock(x, y, z);
        for (int distance = 0; distance < drawDistance; distance++)
        {
            y--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                world.setBlock(x, y, z, 0);
                break;
            }

            x--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                world.setBlock(x, y, z, 0);
                break;
            }

            z--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                world.setBlock(x, y, z, 0);
                break;
            }
        }
    }

    //take in the screen cords and return block cords to be modified

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
    private int drawDistance = 150;

    //Render Size
    private int xCamRez = 29;
    private int yCamRez = 61;


}
