package GUI.Menus.Renderer.raycastRendering;

import World.DataStorage.Octree.ActiveBranch;

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

    GameData.gameData gameData;
    //private World world = new World();

    private int[][][] culledCoordMods;
    private CastedBlock[][] castedBlocks;

    public RaycastRenderer(GameData.gameData gameData) {
        super(gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ, TYPE_4BYTE_ABGR_PRE);
        this.drawingManager = new GridDrawingManager(gameData ,gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ);
        this.gameData = gameData;
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
                castedBlocks[x][y] = pathLeftFace(castedBlocks[x][y]);

                drawingManager.drawTopBlock(castedBlocks[x][y].getScreenX(), castedBlocks[x][y].getScreenY(),
                        castedBlocks[x][y].getType(),
                        pathRightTop(culledCoordMods[x][y][0] + gameData.playerXCamCor, culledCoordMods[x][y][1]+ gameData.playerYCamCor));
            }
        }
        graphics.drawImage(drawingManager, 0, 0, null);
        graphics.setColor(Color.BLACK);
        graphics.fillOval(((xCamRez) * 32)-5, ((xCamRez) * 16)+25, 10, 10);
    }

    //GetCastedBlock
    private CastedBlock pathLeftFace(CastedBlock castedBlock){
        long x = castedBlock.getScreenX() + gameData.playerXCamCor;
        long y = castedBlock.getScreenY() + gameData.playerYCamCor;
        long z = gameData.playerZCamCor;

        int block = 0;

        for (int distance = 0; distance < drawDistance; distance++)
        {
            x--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                castedBlock.set(new long[]{x, y, z}, new int[]{block, 4});
                return castedBlock;
            }

            y--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                castedBlock.set(new long[]{x, y, z}, new int[]{block, 2});
                return castedBlock;
            }

            z--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                castedBlock.set(new long[]{x, y, z}, new int[]{block, 0});
                return castedBlock;
            }
        }
        castedBlock.set(new long[]{x, y, z}, new int[]{1, 0});
        return castedBlock;
    }

    private int[] pathLeftTop(long x, long y){
        long z = gameData.playerZCamCor;
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
        long z = gameData.playerZCamCor;
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

        long x = culledCoordMods[xCamRez/2][yCamRez/2][0] + gameData.playerXCamCor;
        long y = culledCoordMods[xCamRez/2][yCamRez/2][1] + gameData.playerYCamCor;

        long z = gameData.playerZCamCor;

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

        long x = culledCoordMods[xCamRez/2][yCamRez/2][0] + gameData.playerXCamCor;
        long y = culledCoordMods[xCamRez/2][yCamRez/2][1] + gameData.playerYCamCor;

        long z = gameData.playerZCamCor;

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
        CastedBlock[][] newCastedBlocks = new CastedBlock[xCamRez][yCamRez];
        //cycle through cam cords
        for (int y = 0; y < yCamRez; y++){
            int[] rowOrginMod = new int[]{y/2, y/2};
            if (y%2 != 0){
                rowOrginMod[0]++;
            }
            for (int x = 0; x < xCamRez; x++){
                CoordMods[x][y] = new int[]{rowOrginMod[0] + x, rowOrginMod[1] - x};
                newCastedBlocks[x][y] = new CastedBlock(new int[]{rowOrginMod[0] + x, rowOrginMod[1] - x});
            }
        }
        this.culledCoordMods = CoordMods;
        this.castedBlocks = newCastedBlocks;
    }

    //draw distance
    private int drawDistance = 150;

    //Render Size
    private int xCamRez = 29;
    private int yCamRez = 61;


}
