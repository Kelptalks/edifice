package GUI.IsoRenderer.Controls;

import GUI.IsoRenderer.Camera.CameraData;
import GUI.IsoRenderer.Camera.GridManager.Structure.CastedBlock;
import GameData.GameData;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayKeyInputs implements KeyListener {
    private GameData gameData;
    private CameraData cameraData;
    public PlayKeyInputs(GameData gameData, CameraData cameraData){
        this.gameData = gameData;
        this.cameraData = cameraData;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    private int blockNum = 1;
    int z = 0;

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'r'){
            blockNum++;
            if (blockNum > gameData.blocks.length - 1){
                blockNum = 1;
            }
            gameData.currentBlock = gameData.blocks[blockNum];
        }

        if (e.getKeyChar() == 'e') {
            rotateCamera(CameraAngle.values()[z]);
            z++;
            if (z == 4){
                z = 0;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void rotateCamera(CameraAngle cameraAngle){
        if (cameraData.xCastingDirection != cameraAngle.xDirection) {
            cameraData.xCastingDirection = cameraAngle.xDirection;
            //Calculate xOffSet due to rotation
            long x1 = gameData.keyMod.keyToCords(cameraData.castedChunkManager.screenCordsToBlockKey(1920 / 2, 1080 / 2))[0];
            long x2 = gameData.keyMod.keyToCords(cameraData.camWorldKey)[0];
            long xDiff = (x2 - x1) * 2;
            cameraData.camWorldKey = gameData.keyMod.getRelativeKey(cameraData.camWorldKey, 0, -xDiff, 0, 0);
        }

        if (cameraData.yCastingDirection != cameraAngle.yDirection) {
            cameraData.yCastingDirection = cameraAngle.yDirection;
            //Calculate yOffSet due to rotation
            long y1 = gameData.keyMod.keyToCords(cameraData.castedChunkManager.screenCordsToBlockKey(1920 / 2, 1080 / 2))[1];
            long y2 = gameData.keyMod.keyToCords(cameraData.camWorldKey)[1];
            long yDiff = (y2 - y1) * 2;
            cameraData.camWorldKey = gameData.keyMod.getRelativeKey(cameraData.camWorldKey, 0, 0, -yDiff, 0);
        }

        cameraData.castedChunkManager.setWorldKeys();
        cameraData.castedChunkManager.renderChunks();
    }

    public void flipYViewingAngle(){
        long y1 = gameData.keyMod.keyToCords(cameraData.castedChunkManager.screenCordsToBlockKey(1920/2, 1080/2))[1];
        long y2 = gameData.keyMod.keyToCords(cameraData.camWorldKey)[1];
        long diff = (y2 - y1) * 2;

        cameraData.camWorldKey = gameData.keyMod.getRelativeKey(cameraData.camWorldKey, 0, 0, -diff, 0);

        cameraData.yCastingDirection *= -1;
        cameraData.castedChunkManager.setWorldKeys();
        cameraData.castedChunkManager.renderChunks();
    }

    public void flipXViewingAngle(){
        long key = cameraData.castedChunkManager.screenCordsToBlockKey(1920/2, 1080/2);
        long x1 = gameData.keyMod.keyToCords(key)[0];
        long x2 = gameData.keyMod.keyToCords(cameraData.camWorldKey)[0];
        long diff = (x2 - x1) * 2;
        cameraData.camWorldKey = gameData.keyMod.getRelativeKey(cameraData.camWorldKey, 0, -diff, 0, 0);

        cameraData.xCastingDirection *= -1;
        cameraData.castedChunkManager.setWorldKeys();
        cameraData.castedChunkManager.renderChunks();
    }
}
