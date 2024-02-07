package GUI.IsoRenderer.GridManager;

import GameData.GameData;

public class CastedBlockCuller {

    private GameData gameData;
    public CastedBlockCuller(GameData gameData){
        this.gameData = gameData;
    }

    //create an array of CastedBlocks that fits in a rectangle
    public CastedBlock[][] getCulledCordMods(int xCamRez, int yCamRez){

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

        setCulledCordWorldKeys(newCastedBlocks);
        return newCastedBlocks;
    }


    //updates all of the cast blocks world keys.
    public void setCulledCordWorldKeys(CastedBlock[][] castedBlocks){
        //loop through all CastedBlocks blocks
        for(CastedBlock[] castedBlocksRow : castedBlocks){
            for(CastedBlock castedBlock : castedBlocksRow){
                //Get the screen cords
                int[] isoScreenCords = castedBlock.getIsoScreenCords();
                //use the screen cords and player cords to set the rendering plane
                long key = gameData.keyMod.getRelativeKey(gameData.playerCamKey, 0, isoScreenCords[0], isoScreenCords[1], 0);
                castedBlock.setWorldKey(key);
            }
        }
    }
}
