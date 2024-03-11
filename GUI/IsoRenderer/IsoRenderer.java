package GUI.IsoRenderer;

import GUI.IsoRenderer.Camera.Camera;
import GUI.IsoRenderer.Camera.CameraData;
import GUI.IsoRenderer.Camera.Gui.Window;
import GUI.IsoRenderer.Controls.PlayKeyInputs;
import GUI.IsoRenderer.Controls.PlayerMouseInputs;
import GUI.IsoRenderer.Camera.GridManager.Structure.CastedBlock;
import GUI.IsoRenderer.Camera.GridManager.Structure.CastedBlockCuller;
import GUI.IsoRenderer.Camera.GridManager.GridManager;
import GUI.IsoRenderer.Camera.RayCaster.RayCaster;
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

    private CameraData cameraData;
    private Camera camera;
    private Window window;
    private PlayerMouseInputs playerMouseInputs;
    private PlayKeyInputs playerKeyInputs;
    public IsoRenderer(GameData gameData){
        //Setup camera rendering
        this.cameraData = new CameraData(gameData);
        this.camera = new Camera(gameData, cameraData);

        //Setup GUI rendering
        this.window = new Window(gameData, cameraData);
        cameraData.window = this.window;

        //setUp player inputs
        //Keyboard inputs
        this.playerKeyInputs = new PlayKeyInputs(gameData, cameraData);
        this.addKeyListener(playerKeyInputs);
        //Mouse inputs
        this.playerMouseInputs = new PlayerMouseInputs(gameData, cameraData);
        this.addMouseListener(playerMouseInputs);
        this.addMouseMotionListener(playerMouseInputs);

        //Other
        this.setPreferredSize(new Dimension(gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ));
        this.setFocusable(true);
        repaint();
    }

    //The main rendering loop
    @Override
    public void run() {
        while (true){
            this.renderFrame();
        }
    }

    //renders a new frame and draws it to the panel
    public void renderFrame(){
        //draw the castedBlocks blocks
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        camera.renderFrame();
        g2D.drawImage(this.camera, 0, 0, this);

        if (window.isVisible()) {
            g2D.drawImage(this.window, window.getXScreenCor(), window.getYScreenCor(), this);
        }
    }
}
