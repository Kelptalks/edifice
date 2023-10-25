package Visuals;

import Constants.Constants;
import Visuals.raycastRendering.RaycastRenderer;

import javax.swing.*;
import java.awt.*;

public class ViewPort extends JPanel {
    private RaycastRenderer rayCaster = new RaycastRenderer(Constants.SCREEN_X_REZ, Constants.SCREEN_Y_REZ);

    public ViewPort(){
        this.setPreferredSize(new Dimension(Constants.SCREEN_X_REZ, Constants.SCREEN_Y_REZ));
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