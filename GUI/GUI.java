package GUI;

import GUI.IsoRenderer.IsoRenderer;
import GUI.PlayerConrolMenu.PlayerControlMenu;
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
        IsoRenderer isoRenderer = new IsoRenderer(gameData);
        PlayerControlMenu playerControlMenu = new PlayerControlMenu(gameData);

        // Add components to layout
        this.add(playerControlMenu, BorderLayout.WEST);
        this.add(isoRenderer, BorderLayout.EAST);
        this.pack();
        this.setVisible(true);
        isoRenderer.run();
    }
}