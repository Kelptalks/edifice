package GUI.IsoRenderer;

import GameData.GameData;

import javax.swing.*;
import java.awt.*;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  IsoRenderer
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Class to manage the drawing
 *  of the 3D isometric world.
 *  Managing the highest level
 *  of rendering.
 */
public class IsoRenderer extends JPanel implements Runnable {

    private Canvas canvas;
    public IsoRenderer(GameData gameData){
        this.canvas = new Canvas(gameData,1920, 1080);
        this.setPreferredSize(new Dimension(gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ));
        repaint();
    }

    //The main rendering loop
    @Override
    public void run() {
        while (true){
            //pause the thread
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.renderFrame();
        }
    }

    //renders a new frame and draws it to the panel
    public void renderFrame(){
        long t1 = System.nanoTime();
        canvas.renderFrame();
        this.repaint();
        long t2 = System.nanoTime();

        //Print frame rendering times
        //System.out.println("Frame rendered(Time : " + (t2 - t1) + ")");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(this.canvas, 0, 0, this);
    }
}
