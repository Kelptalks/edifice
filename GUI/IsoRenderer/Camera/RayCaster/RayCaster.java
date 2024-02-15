package GUI.IsoRenderer.Camera.RayCaster;

import GUI.IsoRenderer.Camera.GridManager.Structure.CastedBlock;
import GUI.IsoRenderer.Textures.Texture;
import GameData.GameData;
import GameData.Block;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RayCaster {

    private final GameData gameData;
    public RayCaster(GameData gameData){
        this.gameData = gameData;

    }


    //creates an array that contains instructions the direction the ray is cast.
    public void createOrder(String order){

    }



    public void castBlocks(CastedBlock[][] castedBlocks){
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (CastedBlock[] castedBlockRow : castedBlocks){
            for (CastedBlock castedBlock : castedBlockRow){
                CastingThread castingRunnable = new CastingThread(this, castedBlock);
                executor.execute(castingRunnable);
            }
        }
        executor.shutdown(); // Stop accepting new tasks
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void castBlock(CastedBlock castedBlock){
        castLeft(castedBlock);
        castRight(castedBlock);
    }

    public void castLeft(CastedBlock castedBlock){
        long key = castedBlock.getWorldKey();
        int distance = 0;
        key = gameData.keyMod.modAxis(key, 0, 2, 200);
        Block block;
        while(distance < gameData.drawDistance){
            distance++;

            key = gameData.keyMod.modAxis(key, 0, 0, -1);
            block = gameData.blocks[gameData.activeArea.getBlock(key)];
            if (!block.transparent){
                castedBlock.setBlockType(0, block);
                castedBlock.setLeftTexture(Texture.RightTopFace);
                castedBlock.setLeftFilter(Texture.RightTopFace);
                break;
            }

            key = gameData.keyMod.modAxis(key, 0, 1, -1);
            block = gameData.blocks[gameData.activeArea.getBlock(key)];
            if (!block.transparent){
                castedBlock.setBlockType(0, block);
                castedBlock.setLeftTexture(Texture.LeftBotFace);
                break;
            }

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

    public void castRight(CastedBlock castedBlock){
        long key = castedBlock.getWorldKey();
        int distance = 0;
        key = gameData.keyMod.modAxis(key, 0, 2, 200);
        Block block;

        while(distance < gameData.drawDistance){
            distance++;

            key = gameData.keyMod.modAxis(key, 0, 1, -1);
            block = gameData.blocks[gameData.activeArea.getBlock(key)];
            if (!block.transparent){
                castedBlock.setBlockType(1, block);
                castedBlock.setRightTexture(Texture.LeftTopFace);
                break;
            }

            key = gameData.keyMod.modAxis(key, 0, 0, -1);
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
                    //castedBlock.setRightFilter(Texture.TopRightFace);
                    break;
                }

                //y side\
                long yKey = gameData.keyMod.modAxis(key, 0, 1, 1);
                block = gameData.blocks[gameData.activeArea.getBlock(yKey)];
                if (!block.transparent) {
                    castedBlock.setRightFilter(Texture.BotBotHalfRight);

                }

                //x side
                key = gameData.keyMod.modAxis(key, 0, 0, -1);
                block = gameData.blocks[gameData.activeArea.getBlock(key)];
                if (!block.transparent) {
                    castedBlock.setRightFilter(Texture.TopTopHalfRight);

                }

                key = gameData.keyMod.modAxis(key, 0, 1, 1);
                block = gameData.blocks[gameData.activeArea.getBlock(key)];
                if (!block.transparent) {
                    castedBlock.setRightFilter(Texture.TopRightFace);
                    break;
                }
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
                    //castedBlock.setRightFilter(Texture.TopRightFace);
                    break;
                }

                //y side\
                long yKey = gameData.keyMod.modAxis(key, 0, 1, 1);
                block = gameData.blocks[gameData.activeArea.getBlock(yKey)];
                if (!block.transparent) {
                    castedBlock.setLeftFilter(Texture.BotBotHalfLeft);
                }

                //x side
                key = gameData.keyMod.modAxis(key, 0, 0, -1);
                block = gameData.blocks[gameData.activeArea.getBlock(key)];
                if (!block.transparent) {
                    castedBlock.setLeftFilter(Texture.TopTopHalfLeft);

                }

                key = gameData.keyMod.modAxis(key, 0, 1, 1);
                block = gameData.blocks[gameData.activeArea.getBlock(key)];
                if (!block.transparent) {
                    castedBlock.setLeftFilter(Texture.TopLeftFace);
                    break;
                }
            }
        }
    }


}
