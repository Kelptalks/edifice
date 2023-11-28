package GUI.Menus.Renderer.raycastRendering;

import GameData.GameData;
import World.ActiveBranch.ActiveArea;
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

    private ActiveArea world;

    GameData gameData;
    //private World world = new World();

    private int[][][] culledCoordMods;
    private CastedBlock[][] castedBlocks;

    public RaycastRenderer(GameData gameData) {
        super(gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ, TYPE_4BYTE_ABGR_PRE);
        this.drawingManager = new GridDrawingManager(gameData ,gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ);
        this.gameData = gameData;
        this.world = new ActiveArea(new World(), 576460750000000000L, 7);
        updateCulledCoordMods();
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Ray casting
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */
    public void rayCast(){
        for (int y = 0; y < gameData.yCamRez; y++){
            for (int x =  0; x < gameData.xCamRez; x++) {
                castedBlocks[x][y] = rayCast(castedBlocks[x][y], new int[]{1, 0, 2}, 1);
                castedBlocks[x][y] = rayCast(castedBlocks[x][y], new int[]{0, 1, 2}, 0);
                castedBlocks[x][y] = castShadows(castedBlocks[x][y]);

                drawingManager.drawCastedBlock(castedBlocks[x][y]);
            }
        }
        graphics.drawImage(drawingManager, 0, 0, null);
        graphics.setColor(Color.BLACK);
        graphics.fillOval(((gameData.xCamRez) * 32)-5, ((gameData.xCamRez) * 16)+25, 10, 10);
        castShadows(castedBlocks[gameData.xCamRez/2][gameData.yCamRez/2]);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Cam to block casting
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */
    //cast a block using a specific order
    private CastedBlock rayCast(CastedBlock castedBlock, int[] order, int side){
        long[] cords = new long[3];
        cords[0] = (castedBlock.getScreenX() + gameData.playerXCamCor);
        cords[1] = (castedBlock.getScreenY() + gameData.playerYCamCor);
        cords[2] = (gameData.playerZCamCor);

        for (int distance = 0; distance < gameData.drawDistance; distance++)
        {
            for (int axis = 0; axis < 3; axis++){
                cords[order[axis]]--;
                int block = world.getBlock(cords[0], cords[1], cords[2]);
                if (block != 0){
                    castedBlock.setTriangleBlockCords(side, new long[]{cords[0], cords[1], cords[2]});
                    castedBlock.setTriangleTexture(side, new int[]{block, (order[axis]) + (side*3)});
                    return castedBlock;
                }
            }
        }
        return castedBlock;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Shadows
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * pathing shadows, by pathing
     * from a block to the left, and
     * if a block is hit, then draw
     * shadow on that triangle.
     */

    public CastedBlock castShadows(CastedBlock castedBlock){
        castedBlock.setTriangleShader(0, 0);
        castedBlock.setTriangleShader(1, 0);

        if (castedBlock.getTriangleTexture(0)[1] == 0){
            castedBlock.setTriangleShader(0, 4);
        }

        return castedBlock;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Editing blocks
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */

    public void rayCastAndPlace(int placingBlock){
        CastedBlock castedBlock = castedBlocks[gameData.xCamRez/2][gameData.yCamRez/2];
        long[] blockCor = castedBlock.getTriangleBlockCords(0);
        int x = 0;
        int y = 0;
        int z = 1;
        int triangle1 = castedBlock.getTriangleTexture(1)[1];
        int triangle2 = castedBlock.getTriangleTexture(1)[0];

        System.out.println(triangle1);
        if (triangle2 == 2){
            System.out.println("Right");
            x = 1;
            z = 0;
        }
        if (triangle1 == 4){
            System.out.println("left");
            y = 1;
            z = 0;
        }
        world.setBlock(blockCor[0] + x, blockCor[1] + y, blockCor[2] + z, placingBlock);
    }

    public void pathAndPlace(int placingBlock){

        long x = culledCoordMods[gameData.xCamRez/2][gameData.yCamRez/2][0] + gameData.playerXCamCor;
        long y = culledCoordMods[gameData.xCamRez/2][gameData.yCamRez/2][1] + gameData.playerYCamCor;
        long z = gameData.playerZCamCor;

        int block = world.getBlock(x, y, z);
        for (int distance = 0; distance < gameData.drawDistance; distance++)
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

        long x = culledCoordMods[gameData.xCamRez/2][gameData.yCamRez/2][0] + gameData.playerXCamCor;
        long y = culledCoordMods[gameData.xCamRez/2][gameData.yCamRez/2][1] + gameData.playerYCamCor;

        long z = gameData.playerZCamCor;

        System.out.println(x);
        System.out.println(y);
        int block = world.getBlock(x, y, z);
        for (int distance = 0; distance < gameData.drawDistance; distance++)
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
        int[][][] CoordMods = new int[gameData.xCamRez][gameData.yCamRez][2];
        CastedBlock[][] newCastedBlocks = new CastedBlock[gameData.xCamRez][gameData.yCamRez];
        //cycle through cam cords
        for (int y = 0; y < gameData.yCamRez; y++){
            int[] rowOrginMod = new int[]{y/2, y/2};
            if (y%2 != 0){
                rowOrginMod[0]++;
            }
            for (int x = 0; x < gameData.xCamRez; x++){
                CoordMods[x][y] = new int[]{rowOrginMod[0] + x, rowOrginMod[1] - x};
                newCastedBlocks[x][y] = new CastedBlock(new int[]{rowOrginMod[0] + x, rowOrginMod[1] - x});
            }
        }
        this.culledCoordMods = CoordMods;
        this.castedBlocks = newCastedBlocks;
    }
}
