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

    public void mainMenu(){
        JButton Load = new JButton("Load Save");
        this.add(Load);
        Load.setBounds(Constants.SCREEN_X_REZ/100, Constants.SCREEN_Y_REZ/100, Constants.SCREEN_X_REZ/10, Constants.SCREEN_Y_REZ/15);

        JButton newGame = new JButton("New Game");
        this.add(newGame);
        newGame.setBounds(Constants.SCREEN_X_REZ/100, Constants.SCREEN_Y_REZ/10, Constants.SCREEN_X_REZ/10, Constants.SCREEN_Y_REZ/15);

        Load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeMainMenu(Load, newGame);
                System.out.println("Load Save button clicked!");
            }
        });

        // Add action listener for the New Game button
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to execute when the New Game button is clicked
                removeMainMenu(Load, newGame);
                System.out.println("New Game button clicked!");
            }
        });
    }

    public void removeMainMenu(JButton Load, JButton newGame){
        // Code to execute when the Load button is clicked
        // Assuming Load and newGame are member variables
        this.remove(Load);
        this.remove(newGame);

        // If using a JFrame or JPanel, it's good to repaint and revalidate to ensure the changes take effect
        this.repaint();
        this.revalidate();
    }
}