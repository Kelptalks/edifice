package GUI.Menus.PerlinNoise;
import GUI.Menus.Menu;
import GameData.gameData;
import javax.swing.*;
import java.awt.*;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  PerlinNoiseMenu
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  This is a menu for visualizing
 *  My perlin noise generation
 *  Allowing me to debug more easily
 */
public class PerlinNoiseMenu extends JPanel implements Menu {
    private GameData.gameData gameData;
    private PerlinVisualiser perlinVisualiser;
    public PerlinNoiseMenu(GameData.gameData gameData){
        this.gameData = new gameData();
        this.perlinVisualiser = new PerlinVisualiser(gameData);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(perlinVisualiser, 0, 0, this); // Assuming rayCaster is a BufferedImage
    }

    @Override
    public void setCurrentMenu() {
        gameData.menu = this;
    }
}
