package GUI;

import GUI.Controls.KeyBoardInputs;
import GUI.Controls.MouseInputs;
import GUI.MainMenu.Menu;
import GameData.GameData;
import GUI.Visuals.ViewPort;

import javax.swing.*;

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
    private ViewPort viewPort;
    private GameData gameData;
    private Menu menu;
    public GUI(GameData gameData){
        super();

        this.gameData = gameData;
        this.menu = new Menu(gameData);
        this.viewPort = new ViewPort(gameData);

        this.setSize(gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        this.setVisible(true);
        this.setTitle("Telp");

        //mainMenu();
        this.addMouseListener(new MouseInputs());
        this.addKeyListener(new KeyBoardInputs());

        this.add(viewPort);
    }

    public void renderView(){
        viewPort.renderView();
    }
}