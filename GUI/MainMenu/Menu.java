package GUI.MainMenu;

import GameData.GameData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Escape menu
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Allows access to settings, saving and loading worlds, and other tools.
 */
public class Menu extends JLabel {

    private GameData gameData;
    public Menu(GameData gameData){
        this.gameData = gameData;
        mainMenu();
    }


    //LoadGui
    public void mainMenu(){
        JButton Load = new JButton("Load Save");
        this.add(Load);
        Load.setBounds(gameData.SCREEN_X_REZ/100, gameData.SCREEN_Y_REZ/100, gameData.SCREEN_X_REZ/10, gameData.SCREEN_Y_REZ/15);

        JButton newGame = new JButton("New Game");
        this.add(newGame);
        newGame.setBounds(gameData.SCREEN_X_REZ/100, gameData.SCREEN_Y_REZ/10, gameData.SCREEN_X_REZ/10, gameData.SCREEN_Y_REZ/15);

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
