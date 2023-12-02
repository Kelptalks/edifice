package GUI;

import GUI.PlayerConrolMenu.PlayerControlMenu;
import GUI.Renderer.Renderer;
import GameData.GameData;

import javax.swing.*;
import java.awt.*;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  GameFrame
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 */
public class GUI extends JFrame{
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Constructor
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */

    private GameData gameData;
    public GUI(GameData gameData){
        this.gameData = gameData;

        //Set up the window
        this.setSize(gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Telp");

        this.setLayout(new BorderLayout());

        //Set renderer
        Renderer renderer = new Renderer(gameData);
        PlayerControlMenu playerControlMenu = new PlayerControlMenu(gameData);

        // Add components to layout
        this.add(playerControlMenu, BorderLayout.WEST);
        this.add(renderer, BorderLayout.EAST);
        this.pack();

        this.setVisible(true);
    }
}