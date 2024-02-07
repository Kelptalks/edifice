package GUI.PlayerConrolMenu;

import GUI.PlayerConrolMenu.Controls.PlayerMouseInputs;
import GUI.PlayerConrolMenu.Visuals.MenuVisuals;
import GameData.GameData;

import javax.swing.*;
import java.awt.*;

public class PlayerControlMenu extends JPanel {
    private MenuVisuals visuals;
    public PlayerControlMenu(GameData gameData){
        visuals = new MenuVisuals(gameData,100, 1080);
        this.setPreferredSize(new Dimension(100, 1080));
        this.addMouseListener(new PlayerMouseInputs());
    }


    //paint the rendered buffered image to screen
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(visuals, 0, 0, null);
    }
}
