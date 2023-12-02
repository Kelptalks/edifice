package GUI.Renderer;
import GUI.Renderer.Controls.KeyBoardInputs;
import GUI.Renderer.Controls.MouseInputs;
import GUI.Renderer.Controls.MouseMotionInputs;
import GUI.Renderer.Effects.Effects;
import GUI.Renderer.raycastRendering.RaycastRenderer;
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
public class Renderer extends JPanel{
    private RaycastRenderer rayCaster;
    private Effects effects;
    private GameData gameData;

    public Renderer(GameData gameData){
        this.rayCaster = new RaycastRenderer(gameData);
        this.effects = new Effects(gameData);
        this.setPreferredSize(new Dimension(gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ));

        //add keyInputs
        this.setFocusable(true); //set the focus
        this.addMouseListener(new MouseInputs(gameData, rayCaster));
        this.addKeyListener(new KeyBoardInputs(gameData, rayCaster));
        this.addMouseMotionListener(new MouseMotionInputs(gameData, effects));

        //Start rendering loop
        startRenderLoop();
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Rendering
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */

    private void startRenderLoop(){
        int delay = 0; // milliseconds
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
        g2D.drawImage(rayCaster, -30, -15, this); // Assuming rayCaster is a BufferedImage
        g2D.drawImage(effects, 0, 0, this); // Assuming rayCaster is a BufferedImage
    }

}