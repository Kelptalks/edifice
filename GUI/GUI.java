package GUI;

import GUI.IsoRenderer.IsoRenderer;
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



        //Set renderer
        IsoRenderer isoRenderer = new IsoRenderer(gameData);
        // Add components to layout
        this.add(isoRenderer);
        this.pack();
        this.setVisible(true);
        isoRenderer.run();
    }
}