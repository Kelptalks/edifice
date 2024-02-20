package GUI.IsoRenderer.Camera.GridManager.Structure;

import GUI.IsoRenderer.Camera.CameraData;
import GameData.GameData;

public class CastedChunkCuller {
    private  CameraData cameraData;
    private GameData gameData;
    public CastedChunkCuller(GameData gameData, CameraData cameraData){
        this.gameData = gameData;
        this.cameraData = cameraData;
    }
    public CastedChunk[][] getCulledCordMods(){
        int xCamRez = cameraData.camChunkXRez;
        int yCamRez = cameraData.camChunkYRez;
        CastedChunk[][] newCastedChunks = new CastedChunk[xCamRez][yCamRez];
        //cycle through cam cords
        for (int y = 0; y < yCamRez; y++){
            int[] rowOrginMod = new int[]{y/2, y/2};
            if (y%2 != 0){
                rowOrginMod[0]++;
            }
            for (int x = 0; x < xCamRez; x++){
                newCastedChunks[x][y] = new CastedChunk(cameraData);
                newCastedChunks[x][y].setIsoCords(new int[]{(rowOrginMod[0] + x), (rowOrginMod[1] - x)});
            }
        }
        return newCastedChunks;
    }



    public void setCulledCordWorldKeys(CastedChunk[][] castedChunks){
        //loop through all CastedBlocks blocks
        for(CastedChunk[] castedChunkRow : castedChunks){
            for(CastedChunk castedChunk : castedChunkRow){
                //Get the screen cords
                int[] isoScreenCords = castedChunk.getIsoCords();
                //use the screen cords and player cords to set the rendering plane
                long key = gameData.keyMod.getRelativeKey(cameraData.camWorldKey, 0, isoScreenCords[0] * cameraData.xChunkRez, isoScreenCords[1] * cameraData.yChunkRez, 0);
                castedChunk.setWorldKey(key);
                cameraData.castedBlockCuller.setCulledCordWorldKeys(castedChunk.getCastedBlocks(), key);
            }
        }
    }

    public void shiftChunkX(int i) {
        CastedChunk[][] castedChunks = cameraData.castedChunks;
        for(CastedChunk[] castedChunkRow : castedChunks){
            for(CastedChunk castedChunk : castedChunkRow) {
                int[] currentIsoCords = castedChunk.getIsoCords();
                if (i > 0) {
                    castedChunk.setIsoCords(new int[]{currentIsoCords[0] - 1, currentIsoCords[1] + 1});
                }
                if (i < 0) {
                    castedChunk.setIsoCords(new int[]{currentIsoCords[0] + 1, currentIsoCords[1] - 1});
                }
            }
        }
        setCulledCordWorldKeys(castedChunks);

        for(CastedChunk[] castedChunkRow : castedChunks){
            for(CastedChunk castedChunk : castedChunkRow) {
                castedChunk.renderChunk();
            }
        }

        cameraData.castedChunks = castedChunks;
    }
}
