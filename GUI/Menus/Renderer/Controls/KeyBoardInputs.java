package GUI.Menus.Renderer.Controls;

import GUI.Menus.Renderer.raycastRendering.RaycastRenderer;
import GameData.GameData;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyBoardInputs implements KeyListener {

    private GameData gameData;
    private int currentBlock = 1;
    private RaycastRenderer rayCaster;
    public KeyBoardInputs(GameData gameData, RaycastRenderer rayCaster) {
        this.rayCaster = rayCaster;
        this.gameData = gameData;
    }

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
                gameData.playerYCamCor += 1;
                break;
            case KeyEvent.VK_W:
                //move the camera up
                gameData.playerXCamCor -= 1;
                break;
            case KeyEvent.VK_S:
                //move the camera down
                gameData.playerXCamCor += 1;
                break;
            case KeyEvent.VK_D:
                //move the camera right
                gameData.playerYCamCor -= 1;
                break;
            case KeyEvent.VK_E:
                //move the camera right
                gameData.playerZCamCor += 1;
                break;
            case KeyEvent.VK_Q:
                //move the camera right
                gameData.playerZCamCor -= 1;
                break;
            case KeyEvent.VK_SPACE:
                //move the camera right
                rayCaster.rayCastAndPlace(currentBlock);
                break;
            case KeyEvent.VK_R:
                //move the camera right
                currentBlock++;
                if (currentBlock > 7){
                    currentBlock = 1;
                }
                break;
            case KeyEvent.VK_X:
                //move the camera right
                rayCaster.pathAndRemove();
                break;

            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
