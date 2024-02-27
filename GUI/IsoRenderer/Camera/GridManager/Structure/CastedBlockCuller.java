package GUI.IsoRenderer.Camera.GridManager.Structure;

import GUI.IsoRenderer.Camera.CameraData;
import GameData.GameData;

public class CastedBlockCuller {

    private final GameData gameData;
    private final CameraData cameraData;
    public CastedBlockCuller(GameData gameData, CameraData cameraData){
        this.cameraData = cameraData;
        this.gameData = gameData;
    }

    //create an array of CastedBlocks that fits in a rectangle
    public CastedBlock[][] getCulledCordMods(int xCamRez, int yCamRez){

        CastedBlock[][] newCastedBlocks = new CastedBlock[xCamRez][yCamRez];
        //cycle through cam cords
        for (int y = 0; y < yCamRez; y++){
            int[] rowOrginMod = new int[]{y/2, y/2};
            if (y%2 != 0){
                rowOrginMod[0]++;
            }
            for (int x = 0; x < xCamRez; x++){
                newCastedBlocks[x][y] = new CastedBlock(new int[]{rowOrginMod[0] + x, rowOrginMod[1] - x});
            }
        }
        return newCastedBlocks;
    }

    public CastedBlock[][] getIsoCastedBlock(int xCamRez, int yCamRez){
        CastedBlock[][] newCastedBlocks = new CastedBlock[xCamRez][yCamRez];
        for (int y = 0; y < yCamRez; y++){
            for (int x = 0; x < xCamRez; x++){
                newCastedBlocks[x][y] = new CastedBlock(new int[]{x, y});
            }
        }
        return newCastedBlocks;
    }

    //updates all the castedBlocks world keys based off a camera world key
    public void setCulledCordWorldKeys(CastedBlock[][] castedBlocks, long worldKey){
        //loop through all CastedBlocks blocks
        for(CastedBlock[] castedBlocksRow : castedBlocks){
            for(CastedBlock castedBlock : castedBlocksRow){
                //Get the screen cords
                int[] isoScreenCords = castedBlock.getIsoScreenCords();
                //use the screen cords and player cords to set the rendering plane
                long key = gameData.keyMod.getRelativeKey(worldKey, 0, cameraData.xCastingDirection * isoScreenCords[0], cameraData.yCastingDirection * isoScreenCords[1], 0);
                castedBlock.setWorldKey(key);
            }
        }
    }
}
