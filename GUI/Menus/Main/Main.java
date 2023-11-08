package GUI.Menus.Main;
import GUI.GUI;
import GUI.Menus.Menu;
import GameData.gameData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Main menu
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * A menu that has a set of buttons
 * That direct to other menus
 */
public class Main extends JPanel implements Menu {
    private GameData.gameData gameData;
    private GUI GUI;
    public Main(GameData.gameData gameData, GUI GUI){
        this.gameData = new gameData();
        this.GUI = GUI;
        this.setPreferredSize(new Dimension(gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ));
        this.setLayout(null);

        addButtons();
    }

    public void addButtons(){
        JButton start = new JButton();
        start.setBounds(50, 50, 200, 50);
        start.setText("Start");
        start.setVisible(true);
        // Add action listener to button
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.setMenu("renderer");
            }
        });

        this.add(start);

        JButton tests = new JButton();
        tests.setBounds(50, 200, 200, 50);
        tests.setText("Tests");
        tests.setVisible(true);
        // Add action listener to button
        tests.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.setMenu("perlinTest");
            }
        });
        this.add(tests);
    }

    @Override
    public void setCurrentMenu() {
        gameData.menu = this;
    }
}
