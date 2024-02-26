package GUI.IsoRenderer.Controls;

import GUI.IsoRenderer.Camera.CameraData;
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
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'e'){
            blockNum++;
            if (blockNum > gameData.blocks.length - 1){
                blockNum = 1;
            }
            gameData.currentBlock = gameData.blocks[blockNum];
        }

        if (e.getKeyChar() == 'w'){
            cameraData.yCamOffSet+=50;
        }

        if (e.getKeyChar() == 'a'){
            cameraData.xCamOffSet+=50;
        }

        if (e.getKeyChar() == 's'){
            cameraData.yCamOffSet-=50;
        }

        if (e.getKeyChar() == 'd'){
            cameraData.xCamOffSet-=50;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
