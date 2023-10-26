package GUI;

import GUI.Menus.Renderer.Controls.KeyBoardInputs;
import GUI.Menus.Renderer.Controls.MouseInputs;
import GameData.GameData;
import GUI.Menus.Renderer.Renderer;

import javax.swing.*;
import java.awt.*;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  GameFrame
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 */
public class GUI extends JFrame {
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Constructor
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */

    private GameData gameData;

    public GUI(GameData gameData){
        super();

        this.gameData = gameData;

        this.setSize(gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Telp");

        //add keyInputs

        gameData.menu = new Renderer(gameData);
        this.updateMenu();

        this.setVisible(true);
    }

    public void updateMenu(){
        this.add((Component) gameData.menu);
        this.revalidate();
        this.repaint();
    }

}