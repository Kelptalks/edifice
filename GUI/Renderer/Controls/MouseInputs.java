package GUI.Renderer.Controls;

import GUI.Renderer.Renderer;
import GUI.Renderer.raycastRendering.RaycastRenderer;
import GameData.GameData;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class MouseInputs implements MouseListener {
    private GameData gameData;
    private Renderer renderer;

    private RaycastRenderer rayCaster;

    public MouseInputs(GameData gameData, RaycastRenderer rayCaster) {
        this.rayCaster = rayCaster;
        this.gameData = gameData;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
            rayCaster.rayCastAndPlace(e.getX(), e.getY(), gameData.currentBlock);
        }
        else {
            rayCaster.rayCastAndBreak(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
