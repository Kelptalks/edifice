package GUI.IsoRenderer;

import GUI.IsoRenderer.Camera.Camera;
import GUI.IsoRenderer.Camera.CameraData;
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

    private Canvas canvas;
    private CastedBlockCuller castedBlockCuller;
    private CastedBlock[][] castedBlocks;
    private RayCaster rayCaster;
    private GridManager gridManager;
    private GameData gameData;
    private CameraData cameraData;
    private Camera camera;

    private PlayerMouseInputs playerMouseInputs;
    public IsoRenderer(GameData gameData){
        this.gameData = gameData;

        this.castedBlockCuller = new CastedBlockCuller(gameData);
        this.castedBlocks = castedBlockCuller.getCulledCordMods(gameData.xCamRez, gameData.yCamRez);
        this.rayCaster = new RayCaster(gameData);
        this.gridManager = new GridManager();

        this.cameraData = new CameraData(gameData);
        this.camera = new Camera(gameData, cameraData);

        this.addMouseMotionListener(new PlayerMouseInputs(gameData, cameraData));
        this.addMouseListener(new PlayerMouseInputs(gameData, cameraData));

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
        //this.castedBlocks = castedBlockCuller.getCulledCordMods(gameData.xCamRez, gameData.yCamRez);
        //ray cast the screen
        //rayCaster.castBlocks(castedBlocks);

        //draw the castedBlocks blocks
        //canvas.renderFrame(castedBlocks);
        camera.renderFrame();

        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(this.camera, 0, 0, this);
    }
}
