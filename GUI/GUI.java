package GUI;

import GUI.Controls.KeyBoardInputs;
import GUI.Controls.MouseInputs;
import GUI.Menus.Main.Main;
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

        //add keyInputs
        this.setVisible(true);
        this.setTitle("Telp");

        //add keyInputs
        this.addMouseListener(new MouseInputs());
        this.addKeyListener(new KeyBoardInputs());

        gameData.menu = new Renderer(gameData);
        this.updateMenu();

    }

    public void updateMenu(){
        this.add((Component) gameData.menu);
        this.revalidate();
    }

}