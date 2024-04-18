package GUI.IsoRenderer.Camera.GridManager.Structure;

import GUI.IsoRenderer.Camera.CameraData;
import GameData.Block;
import GUI.IsoRenderer.Textures.Texture;
import GameData.GameData;

public class CastedChunkManager {
    private  CameraData cameraData;
    private GameData gameData;
    private CastedChunk[][] castedChunks;
    public CastedChunkManager(GameData gameData, CameraData cameraData){
        this.gameData = gameData;
        this.cameraData = cameraData;

        createCulledChunks();

        setWorldKeys();

        renderChunks();
    }

    //get the casted chunks
    public CastedChunk[][] getChunks(){
        return this.castedChunks;
    }


    //Create a set of isometric chunks that fit a rectangular window.
    public CastedChunk[][] createCulledChunks(){
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
        this.castedChunks = newCastedChunks;
        return newCastedChunks;
    }

    //Set the world keys of the chunks based off cam-key
    public void setWorldKeys(){
        //loop through all CastedBlocks blocks
        for(CastedChunk[] castedChunkRow : castedChunks){
            for(CastedChunk castedChunk : castedChunkRow){
                castedChunk.updateCastedBlocks();
                //Get the screen cords
                int[] isoScreenCords = castedChunk.getIsoCords();
                //use the screen cords and player cords to set the rendering plane
                long key = gameData.keyMod.getRelativeKey(cameraData.camWorldKey, 0, isoScreenCords[0] * cameraData.xChunkRez * cameraData.xCastingDirection, isoScreenCords[1] * cameraData.yChunkRez * cameraData.yCastingDirection, 0);
                castedChunk.setWorldKey(key);
                cameraData.castedBlockCuller.setCulledCordWorldKeys(castedChunk.getCastedBlocks(), key);
            }
        }
    }

    //Render and prep the chunks for display.
    public void renderChunks(){
        for(CastedChunk[] castedChunkRow : castedChunks){
            for(CastedChunk castedChunk : castedChunkRow) {
                castedChunk.setScreenCords(cameraData.gridManager.isoToScreen(castedChunk.getIsoCords(), cameraData.yChunkPixelRez));
                castedChunk.renderChunk();
            }
        }
    }

    //get the canvas cords based off screen cords
    public int[] screenToCanvasCords(int[] cords){
        int xCanvasCord = ((cords[0] - cameraData.xCamOffSet) + cameraData.camXCenterPixel) - (cameraData.xChunkPixelRez/2);
        int yCanvasCord = ((cords[1] - cameraData.yCamOffSet) + cameraData.camYCenterPixel);
        return new int[]{xCanvasCord, yCanvasCord};
    }

    //convert screen cords to the index of a casted chunk
    public int[] screenCordsToCastedChunkIndex(int x, int y){
        int[] canvasCords = screenToCanvasCords(new int[]{x, y});
        int[] isoCords = cameraData.gridManager.ScreenToIso(canvasCords[0], canvasCords[1], cameraData.yChunkPixelRez);
        return isoCordsToIndex(isoCords);
    }

    //convert a screen cords to a casted chunk
    public CastedChunk screenCordsToCastedChunk(int x, int y){
        int[] canvasCords = screenToCanvasCords(new int[]{x, y});
        int[] isoCords = cameraData.gridManager.ScreenToIso(canvasCords[0], canvasCords[1], cameraData.yChunkPixelRez);
        int[] indexCords = isoCordsToIndex(isoCords);
        return castedChunks[indexCords[0]][indexCords[1]];
    }

    //convert screen cords to a casted block
    public CastedBlock screenCordsToCastedBlock(int x, int y) {
        int[] canvasCords = screenToCanvasCords(new int[]{x, y});
        CastedChunk castedChunk = screenCordsToCastedChunk(x, y);

        //get chunk from screen
        int[] isoCords = cameraData.gridManager.ScreenToIso(canvasCords[0], canvasCords[1], cameraData.yChunkPixelRez);

        //get casted block in chunk using scaled offsets
        int[] isoCordsRemainder = cameraData.gridManager.ScreenToIso(canvasCords[0], canvasCords[1], 32);
        isoCordsRemainder[0] = isoCordsRemainder[0] - (isoCords[0] * cameraData.xChunkRez);
        isoCordsRemainder[1] = isoCordsRemainder[1] - (isoCords[1] * cameraData.yChunkRez);

        return castedChunk.getCastedBlocks()[isoCordsRemainder[0]][isoCordsRemainder[1]];


    }

    public long screenCordsToBlockKey(int x, int y){
        int[] canvasCords = screenToCanvasCords(new int[]{x, y});
        CastedChunk castedChunk = screenCordsToCastedChunk(x, y);
        CastedBlock castedBlock = screenCordsToCastedBlock(x, y);

        //get pixel of in casted block
        int pixelCordsRemainder = canvasCords[0] - castedChunk.getScreenX();
        pixelCordsRemainder -= cameraData.gridManager.isoToScreen(castedBlock.getIsoScreenCords(), 32)[0];

        //Return the key of the correct side
        if (pixelCordsRemainder > 32){
            return castedBlock.getRightBLockKey();
        }
        else{
            return castedBlock.getLeftBLockKey();
        }
    }

    public void placeBlock(int x, int y, Block block){
        int[] canvasCords = screenToCanvasCords(new int[]{x, y});
        CastedChunk castedChunk = screenCordsToCastedChunk(x, y);
        CastedBlock castedBlock = screenCordsToCastedBlock(x, y);

        //get pixel of in casted block
        int pixelCordsRemainder = canvasCords[0] - castedChunk.getScreenX();
        pixelCordsRemainder -= cameraData.gridManager.isoToScreen(castedBlock.getIsoScreenCords(), 32)[0];
        long key = 0;

        //Return the key of the correct side
        if (pixelCordsRemainder > 32){
            if(castedBlock.getRightTexture() == Texture.LeftTopFace){
                key = gameData.keyMod.getRelativeKey(castedBlock.getRightBLockKey(), 0, 0, cameraData.yCastingDirection, 0);
            }
            else if (castedBlock.getRightTexture() == Texture.TopRightFace) {
                key = gameData.keyMod.getRelativeKey(castedBlock.getRightBLockKey(), 0, 0, 0, 1);
            }
            else if (castedBlock.getRightTexture() == Texture.RightBotFace) {
                key = gameData.keyMod.getRelativeKey(castedBlock.getRightBLockKey(), 0, cameraData.xCastingDirection, 0, 0);
            }
            gameData.activeArea.setBlock(key, block.id);
        }
        else{
            if(castedBlock.getLeftTexture() == Texture.LeftBotFace){
                key = gameData.keyMod.getRelativeKey(castedBlock.getLeftBLockKey(), 0, 0, cameraData.yCastingDirection, 0);
            }
            else if (castedBlock.getLeftTexture() == Texture.TopLeftFace) {
                key = gameData.keyMod.getRelativeKey(castedBlock.getLeftBLockKey(), 0, 0, 0, 1);
            }
            else if (castedBlock.getLeftTexture() == Texture.RightTopFace) {
                key = gameData.keyMod.getRelativeKey(castedBlock.getLeftBLockKey(), 0, cameraData.xCastingDirection, 0, 0);
            }
        }
        gameData.activeArea.setBlock(key, block.id);
    }

    public void renderChunkArea(int x, int y){

        screenCordsToCastedChunk(x, y + cameraData.yChunkPixelRez/2).renderChunk();
        screenCordsToCastedChunk(x + cameraData.xChunkPixelRez/2, y).renderChunk();
        screenCordsToCastedChunk(x, y - cameraData.yChunkPixelRez/2).renderChunk();
        screenCordsToCastedChunk(x - cameraData.xChunkPixelRez/2, y).renderChunk();
        screenCordsToCastedChunk(x, y).renderChunk();

    }

    public void getRelativeChunk(CastedChunk castedChunk, int xMod, int yMod){
        int[] isoChunkCords = castedChunk.getIsoCords();

        isoChunkCords[0] = isoChunkCords[0] + xMod;
        isoChunkCords[1] = isoChunkCords[1] + yMod;

        int[] index = isoCordsToIndex(isoChunkCords);

        castedChunks[index[0]][index[1]].renderChunk();
    }

    public int[] isoCordsToIndex(int[] IsoCords){
        for(int x = 0; x < castedChunks.length; x++){
            for(int y = 0; y < castedChunks[x].length; y++) {
                int[] isoCords = castedChunks[x][y].getIsoCords();
                if (isoCords[0] == IsoCords[0] && isoCords[1] == IsoCords[1]){
                    return new int[]{x, y};
                }
            }
        }
        return new int[]{0, 0};
    }

    //shift all chunks and add a new set
    public void shiftChunkX() {
        //Shift world key
        cameraData.camWorldKey = gameData.keyMod.getRelativeKey(cameraData.camWorldKey, 0, -cameraData.xChunkRez, cameraData.yChunkRez, 0);



    }

    public void shiftChunkY() {

    }

}
