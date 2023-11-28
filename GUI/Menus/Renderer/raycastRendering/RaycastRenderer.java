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
    private CastedBlock[][] castedBlocks;

    public RaycastRenderer(GameData gameData) {
        super(gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ, TYPE_4BYTE_ABGR_PRE);
        this.drawingManager = new GridDrawingManager(gameData ,gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ);
        this.gameData = gameData;
        this.world = gameData.activeArea;
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
        return castedBlock;
    }

    public CastedBlock castLeft(CastedBlock castedBlock){
        return castedBlock;
    }


    public CastedBlock castRight(CastedBlock castedBlock){
        return castedBlock;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Editing blocks
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Take a mouse screen input and set
     * a block
     */

    //place a block based off the first non-air block and its face type.
    public boolean rayCastAndPlace(int x, int y, int blockType){
        int[] screenCords = drawingManager.screenCorToBlockCor(new int[]{x, y});
        long[] cords = new long[3];
        cords[0] = (screenCords[0] + gameData.playerXCamCor);
        cords[1] = (screenCords[1] + gameData.playerYCamCor);
        cords[2] = (gameData.playerZCamCor);

        int[] order = new int[]{1, 0, 2};

        for (int distance = 0; distance < gameData.drawDistance; distance++)
        {
            for (int axis = 0; axis < 3; axis++){
                cords[order[axis]]--;
                int block = world.getBlock(cords[0], cords[1], cords[2]);
                if (block != 0){
                    if (order[axis] == 2){
                        world.setBlock(cords[0], cords[1], cords[2] + 1, blockType);
                    }
                    else if (order[axis] == 1){
                        world.setBlock(cords[0], cords[1] + 1, cords[2], blockType);
                    }
                    else {
                        world.setBlock(cords[0] + 1, cords[1], cords[2], blockType);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    //remove the block first cast to
    public boolean rayCastAndBreak(int x, int y){
        int[] screenCords = drawingManager.screenCorToBlockCor(new int[]{x, y});
        long[] cords = new long[3];
        cords[0] = (screenCords[0] + gameData.playerXCamCor);
        cords[1] = (screenCords[1] + gameData.playerYCamCor);
        cords[2] = (gameData.playerZCamCor);

        int[] order = new int[]{1, 0, 2};

        for (int distance = 0; distance < gameData.drawDistance; distance++)
        {
            for (int axis = 0; axis < 3; axis++){
                cords[order[axis]]--;
                int block = world.getBlock(cords[0], cords[1], cords[2]);
                if (block != 0){
                    world.setBlock(cords[0], cords[1], cords[2], 0);
                    return true;
                }
            }
        }
        return false;
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
        this.castedBlocks = newCastedBlocks;
    }
}
