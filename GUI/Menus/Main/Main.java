package GUI.Menus.Main;

import GUI.Menus.Menu;
import GameData.GameData;

import javax.swing.*;
import java.awt.*;

public class Main extends JPanel implements Menu {
    private GameData gameData;
    public Main(GameData gameData){
        this.gameData = new GameData();
        this.setPreferredSize(new Dimension(gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ));
        this.setLayout(null);

        JButton jButton = new JButton();
        jButton.setBounds(50, 50, 100, 100);
        jButton.setText("hello!");
        jButton.setVisible(true);
        this.add(jButton);
    }

    @Override
    public void setCurrentMenu() {
        gameData.menu = this;
    }
}
