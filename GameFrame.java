import Constants.Constants;
import Controls.KeyBoardInputs;
import Controls.MouseInputs;
import Visuals.ViewPort;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  GameFrame
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 */
public class GameFrame extends JFrame {
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Constructor
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    private ViewPort viewPort = new ViewPort();
    public GameFrame() throws IOException {
        super();
        this.setSize(Constants.SCREEN_X_REZ, Constants.SCREEN_Y_REZ);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        this.setVisible(true);
        this.setTitle("Telp");

        //mainMenu();
        this.add(viewPort);
        this.addMouseListener(new MouseInputs());
        this.addKeyListener(new KeyBoardInputs());

    }

    public void renderView(){
        viewPort.renderView();
        this.repaint();
    }
}