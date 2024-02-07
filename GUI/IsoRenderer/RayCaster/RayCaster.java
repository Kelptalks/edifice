package GUI.IsoRenderer.RayCaster;

import GUI.IsoRenderer.GridManager.CastedBlock;
import GUI.IsoRenderer.Textures.Texture;
import GameData.GameData;
import GameData.Block;

import java.util.ArrayList;
import java.util.List;
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
    }

    public void checkKey(){

    }

}
