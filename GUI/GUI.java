package GUI;

import GUI.Menus.Main.Main;
import GUI.Menus.PerlinNoise.PerlinNoiseMenu;
import GUI.Menus.Renderer.Renderer;
import GUI.Menus.VisualTester.VisualTester;

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

    private GameData.gameData gameData;

    public GUI(GameData.gameData gameData){
        super();

        this.gameData = gameData;

        this.setSize(gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Telp");

        //add keyInputs
        gameData.menu = new Main(gameData, this);

        this.updateMenu();

        this.setVisible(true);
    }

    public void updateMenu(){
        this.add((Component) gameData.menu);
        this.revalidate();
        this.repaint();
    }

    public void setMenu(String menu){
        //remove the old menu
        this.remove((Component) gameData.menu);

        //set the game menu to the correct menu
        switch (menu) {
            case "renderer" :
                gameData.menu = new Renderer(gameData);
                break;
            case "perlinTest" :
                gameData.menu = new PerlinNoiseMenu(gameData);
                break;
            case "VisualTester":
                gameData.menu = new VisualTester(gameData);
        }

        //add the new menu
        this.add((Component) gameData.menu);

        ((Component) gameData.menu).requestFocus();
        this.revalidate();
        this.repaint();
    }

}