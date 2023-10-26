package GUI;

import Constants.Constants;
import GUI.Controls.KeyBoardInputs;
import GUI.Controls.MouseInputs;
import GUI.MainMenu.Menu;
import Visuals.ViewPort;

import javax.swing.*;
import java.io.IOException;

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
    private ViewPort viewPort = new ViewPort();

    private Menu menu = new Menu();
    public GUI() throws IOException {
        super();
        this.setSize(Constants.SCREEN_X_REZ, Constants.SCREEN_Y_REZ);
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
        //test git
    }
}