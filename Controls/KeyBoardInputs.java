package Controls;

import Constants.PlayerData;

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
                PlayerData.playerXCamCor -= 1;
                PlayerData.playerYCamCor += 1;
                break;
            case KeyEvent.VK_W:
                PlayerData.playerYCamCor -= 1;
                PlayerData.playerXCamCor -= 1;
                break;
            case KeyEvent.VK_S:
                PlayerData.playerYCamCor += 1;
                PlayerData.playerXCamCor += 1;
                break;
            case KeyEvent.VK_D:
                PlayerData.playerXCamCor += 1;
                PlayerData.playerYCamCor -= 1;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
