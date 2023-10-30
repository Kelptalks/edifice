package GUI.Menus.Renderer;

import GUI.Menus.Menu;
import GUI.Menus.Renderer.Controls.KeyBoardInputs;
import GUI.Menus.Renderer.Controls.MouseInputs;
import GUI.Menus.Renderer.raycastRendering.RaycastRenderer;
import GameData.GameData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Renderer
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Manages the rendering of the
 *  the actual game.
 */
public class Renderer extends JPanel implements Menu {
    private RaycastRenderer rayCaster;
    private GameData gameData;

    public Renderer(GameData gameData){
        this.rayCaster = new RaycastRenderer(gameData);
        this.setPreferredSize(new Dimension(gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ));

        //add keyInputs
        this.setFocusable(true); //set the focus
        this.addMouseListener(new MouseInputs(gameData));
        this.addKeyListener(new KeyBoardInputs());

        //Start rendering loop
        startRenderLoop();
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Rendering
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */

    private void startRenderLoop(){
        int delay = 16; // milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                rayCaster.rayCast();
                repaint();
            }
        };

        new Timer(delay, taskPerformer).start();
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Other
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */

    //paint the rendered buffered image to screen
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(rayCaster, 0, 0, this); // Assuming rayCaster is a BufferedImage
    }

    //sets this object as the current menu
    @Override
    public void setCurrentMenu(){
        gameData.menu = this;
    }

}