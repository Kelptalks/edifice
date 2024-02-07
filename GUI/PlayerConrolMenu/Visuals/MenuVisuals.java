package GUI.PlayerConrolMenu.Visuals;

import GameData.GameData;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.BasicStroke;

public class MenuVisuals extends BufferedImage {

    private Graphics graphics = (Graphics2D) this.getGraphics();
    private GameData gameData;
    public MenuVisuals(GameData gameData, int width, int height) {
        super(width, height, TYPE_4BYTE_ABGR_PRE);
        this.gameData = gameData;
        setUp();
    }

    private void setUp(){
        //draw gray rect
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        graphics.setColor(Color.BLACK);

        //draw menu name
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        graphics.setColor(Color.BLACK);

        // Draw the text
        graphics.drawString("Edifice", 10, 20);
    }

    private void drawButton(){

    }

    private void drawIcon(){

    }
}
