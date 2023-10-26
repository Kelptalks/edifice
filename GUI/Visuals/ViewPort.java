package GUI.Visuals;

import GUI.Visuals.raycastRendering.RaycastRenderer;
import GameData.GameData;

import javax.swing.*;
import java.awt.*;

public class ViewPort extends JPanel {
    private RaycastRenderer rayCaster;

    public ViewPort(GameData gameData){
        this.rayCaster = new RaycastRenderer(gameData, gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ);

        this.setPreferredSize(new Dimension(gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(rayCaster, 0, 0, this); // Assuming rayCaster is a BufferedImage
    }

    public void renderView(){
        rayCaster.rayCast();
        paintComponent(this.getGraphics());
    }
}