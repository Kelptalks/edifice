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

        switch (key) {
            case KeyEvent.VK_A:
                GameData.playerXCamCor -= 1;
                GameData.playerYCamCor += 1;
                break;
            case KeyEvent.VK_W:
                GameData.playerYCamCor -= 1;
                GameData.playerXCamCor -= 1;
                break;
            case KeyEvent.VK_S:
                GameData.playerYCamCor += 1;
                GameData.playerXCamCor += 1;
                break;
            case KeyEvent.VK_D:
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
