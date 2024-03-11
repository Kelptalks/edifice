package GUI.IsoRenderer.Camera.RayCaster;

import GUI.IsoRenderer.Camera.CameraData;
import GUI.IsoRenderer.Camera.GridManager.Structure.CastedBlock;
import GUI.IsoRenderer.Camera.GridManager.Structure.CastedChunk;
import GUI.IsoRenderer.Textures.Texture;
import GameData.GameData;
import GameData.Block;


/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * RayCaster
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 */
public class RayCaster {

    private final GameData gameData;
    private final CameraData cameraData;
    public RayCaster(GameData gameData, CameraData cameraData){
        this.gameData = gameData;
        this.cameraData = cameraData;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * High level casting
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Cast a single block or a
     * collection of them
     */

    //ray cast a chunk in other words ray-cast a bunch of blocks
    public void castChunk(CastedChunk castedChunk){
        CastedBlock[][] castedBlocks = castedChunk.getCastedBlocks();
        for (CastedBlock[] castedBlockRow : castedBlocks){
            for (CastedBlock castedBlock : castedBlockRow){
                castBlock(castedBlock);
            }
        }
    }

    //Ray cast a single block
    public void castBlock(CastedBlock castedBlock){
        castedBlock.setLeftFilter(null);
        castedBlock.setRightFilter(null);
        castLeft(castedBlock);
        castRight(castedBlock);
        castRightShadow(castedBlock);
        castLeftShadow(castedBlock);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Ray casting
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Casting rays to identify
     * the first not transparent block
     * hit and select the correct texture
     * based off which side of the block
     * was hit.
     */

    //Cast the left side of the screenCords
    public void castLeft(CastedBlock castedBlock){
        long key = castedBlock.getWorldKey();
        int distance = 0;
        Block block;
        while(distance < gameData.drawDistance){
            distance++;


            //if ray hits top block face
            key = gameData.keyMod.modAxis(key, 0, 0, -1 * cameraData.xCastingDirection);
            block = gameData.blocks[gameData.activeArea.getBlock(key)];
            if (!block.transparent){
                castedBlock.setBlockType(0, block);
                castedBlock.setLeftTexture(Texture.RightTopFace);
                castedBlock.setLeftFilter(Texture.RightTopFace);
                break;
            }

            //if ray hits left block face
            key = gameData.keyMod.modAxis(key, 0, 1, -1 * cameraData.yCastingDirection);
            block = gameData.blocks[gameData.activeArea.getBlock(key)];
            if (!block.transparent){
                castedBlock.setBlockType(0, block);
                castedBlock.setLeftTexture(Texture.LeftBotFace);
                break;
            }

            //if ray hits right block face
            key = gameData.keyMod.modAxis(key, 0, 2, -1);
            block = gameData.blocks[gameData.activeArea.getBlock(key)];
            if (!block.transparent){
                castedBlock.setBlockType(0, block);
                castedBlock.setLeftTexture(Texture.TopLeftFace);
                break;
            }
        }
        castedBlock.setLeftBlockKey(key);
    }

    //Cast the right side of the screenCords
    public void castRight(CastedBlock castedBlock){
        long key = castedBlock.getWorldKey();
        int distance = 0;
        Block block;

        while(distance < gameData.drawDistance){
            distance++;

            //if ray hits top block face
            key = gameData.keyMod.modAxis(key, 0, 1, -1  * cameraData.yCastingDirection);
            block = gameData.blocks[gameData.activeArea.getBlock(key)];
            if (!block.transparent){
                castedBlock.setBlockType(1, block);
                castedBlock.setRightTexture(Texture.LeftTopFace);
                break;
            }

            key = gameData.keyMod.modAxis(key, 0, 0, -1 * cameraData.xCastingDirection);
            block = gameData.blocks[gameData.activeArea.getBlock(key)];
            if (!block.transparent){
                castedBlock.setBlockType(1, block);
                castedBlock.setRightTexture(Texture.RightBotFace);
                castedBlock.setRightFilter(Texture.RightBotFace);
                break;
            }

            key = gameData.keyMod.modAxis(key, 0, 2, -1);
            block = gameData.blocks[gameData.activeArea.getBlock(key)];
            if (!block.transparent){
                castedBlock.setBlockType(1, block);
                castedBlock.setRightTexture(Texture.TopRightFace);
                break;
            }
        }

        castedBlock.setRightBlockKey(key);
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Shadow casting
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * casting rays at a 90-degree
     * angle relative the direction
     * the rays where cast for rendering
     */

    public void castRightShadow(CastedBlock castedBlock) {
        int distance = 0;
        long key = castedBlock.getRightBLockKey();
        Block block;

        if (castedBlock.getRightTexture() == Texture.TopRightFace) {
            while (distance < gameData.drawDistance) {
                distance++;
                key = gameData.keyMod.modAxis(key, 0, 2, 1);
                block = gameData.blocks[gameData.activeArea.getBlock(key)];
                if (!block.transparent) {
                    castedBlock.setRightFilter(Texture.TopRightFace);
                    break;
                }

                //y side\
                long yKey = gameData.keyMod.modAxis(key, 0, 1, 1  * cameraData.yCastingDirection);
                block = gameData.blocks[gameData.activeArea.getBlock(yKey)];
                if (!block.transparent) {
                    castedBlock.setRightFilter(Texture.BotBotHalfRight);
                }

                //x side
                key = gameData.keyMod.modAxis(key, 0, 0, -1 * cameraData.xCastingDirection);
                block = gameData.blocks[gameData.activeArea.getBlock(key)];
                if (!block.transparent) {
                    if (castedBlock.getRightFilter() == Texture.BotBotHalfRight){
                        castedBlock.setRightFilter(Texture.TopRightFace);
                        break;
                    }
                    else {
                        castedBlock.setRightFilter(Texture.TopTopHalfRight);
                    }
                }

                key = gameData.keyMod.modAxis(key, 0, 1, 1   * cameraData.yCastingDirection);
                block = gameData.blocks[gameData.activeArea.getBlock(key)];
                if (!block.transparent) {
                    castedBlock.setRightFilter(Texture.TopRightFace);
                    break;
                }
            }
        }

        if (castedBlock.getRightTexture() == Texture.LeftTopFace) {
            while (distance < gameData.drawDistance) {
                distance++;



                key = gameData.keyMod.modAxis(key, 0, 1, 1 * cameraData.yCastingDirection);

                key = gameData.keyMod.modAxis(key, 0, 0, -1 * cameraData.xCastingDirection);

                block = gameData.blocks[gameData.activeArea.getBlock(key)];
                if (!block.transparent) {
                    castedBlock.setRightFilter(Texture.LeftFaceWest);
                    key = gameData.keyMod.modAxis(key, 0, 2, 1);
                    block = gameData.blocks[gameData.activeArea.getBlock(key)];
                    if (!block.transparent) {
                        castedBlock.setRightFilter(Texture.LeftTopFace);
                        break;
                    }
                }

                key = gameData.keyMod.modAxis(key, 0, 2, 1);
            }
        }
    }

    public void castLeftShadow(CastedBlock castedBlock) {
        int distance = 0;
        long key = castedBlock.getLeftBLockKey();
        Block block;

        if (castedBlock.getLeftTexture() == Texture.TopLeftFace) {
            while (distance < gameData.drawDistance) {
                distance++;
                key = gameData.keyMod.modAxis(key, 0, 2, 1);
                block = gameData.blocks[gameData.activeArea.getBlock(key)];
                if (!block.transparent) {
                    castedBlock.setLeftFilter(Texture.TopLeftFace);
                    break;
                }

                //y side
                long yKey = gameData.keyMod.modAxis(key, 0, 1, 1  * cameraData.yCastingDirection);
                block = gameData.blocks[gameData.activeArea.getBlock(yKey)];
                if (!block.transparent) {
                    castedBlock.setLeftFilter(Texture.BotBotHalfLeft);
                }

                //x side
                key = gameData.keyMod.modAxis(key, 0, 0, -1 * cameraData.xCastingDirection);
                block = gameData.blocks[gameData.activeArea.getBlock(key)];
                if (!block.transparent) {
                    if (castedBlock.getLeftFilter() == Texture.BotBotHalfLeft){
                        castedBlock.setLeftFilter(Texture.TopLeftFace);
                        break;
                    }
                    else {
                        castedBlock.setLeftFilter(Texture.TopTopHalfLeft);
                    }

                }

                key = gameData.keyMod.modAxis(key, 0, 1, 1  * cameraData.yCastingDirection);
                block = gameData.blocks[gameData.activeArea.getBlock(key)];
                if (!block.transparent) {
                    castedBlock.setLeftFilter(Texture.TopLeftFace);
                    break;
                }
            }
        }

        if (castedBlock.getLeftTexture() == Texture.LeftBotFace) {
            while (distance < gameData.drawDistance) {
                distance++;


                key = gameData.keyMod.modAxis(key, 0, 1, 1  * cameraData.yCastingDirection);
                key = gameData.keyMod.modAxis(key, 0, 0, -1  * cameraData.xCastingDirection);
                block = gameData.blocks[gameData.activeArea.getBlock(key)];
                if (!block.transparent) {
                    castedBlock.setLeftFilter(Texture.LeftFaceSouth);
                    key = gameData.keyMod.modAxis(key, 0, 2, 1);
                    block = gameData.blocks[gameData.activeArea.getBlock(key)];
                    if (!block.transparent) {
                        castedBlock.setLeftFilter(Texture.LeftBotFace);
                        break;
                    }
                }
                key = gameData.keyMod.modAxis(key, 0, 2, 1);
            }
        }
    }

}
