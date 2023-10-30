package GUI.Menus.Renderer.Controls;

import GameData.GameData;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyBoardInputs implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        //move the camera based off (WASD)
        //can add key setting customization later
        switch (key) {
            case KeyEvent.VK_A:
                //move the camera left
                GameData.playerXCamCor -= 1;
                GameData.playerYCamCor += 1;
                break;
            case KeyEvent.VK_W:
                //move the camera up
                GameData.playerYCamCor -= 1;
                GameData.playerXCamCor -= 1;
                break;
            case KeyEvent.VK_S:
                //move the camera down
                GameData.playerYCamCor += 1;
                GameData.playerXCamCor += 1;
                break;
            case KeyEvent.VK_D:
                //move the camera right
                GameData.playerXCamCor += 1;
                GameData.playerYCamCor -= 1;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
