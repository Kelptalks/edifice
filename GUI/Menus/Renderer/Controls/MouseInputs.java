package GUI.Menus.Renderer.Controls;

import GUI.Menus.Renderer.Renderer;
import GameData.GameData;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class MouseInputs implements MouseListener {
    private GameData gameData;
    private Renderer renderer;
    public MouseInputs(GameData gameData){
        this.gameData = gameData;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        this.renderer = (Renderer) gameData.menu;
        renderer.modBlock(x, y);
        System.out.println("Mouse clicked at component coordinates: [" + x + ", " + y + "]");
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
