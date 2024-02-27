package GUI.IsoRenderer;

import GUI.IsoRenderer.Camera.Camera;
import GUI.IsoRenderer.Camera.CameraData;
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

    private Canvas canvas;
    private CastedBlockCuller castedBlockCuller;
    private CastedBlock[][] castedBlocks;
    private RayCaster rayCaster;
    private GridManager gridManager;
    private GameData gameData;
    private CameraData cameraData;
    private Camera camera;

    private PlayerMouseInputs playerMouseInputs;
    private PlayKeyInputs playerKeyInputs;
    public IsoRenderer(GameData gameData){
        this.gameData = gameData;

        this.castedBlockCuller = new CastedBlockCuller(gameData, cameraData);
        this.castedBlocks = castedBlockCuller.getCulledCordMods(gameData.xCamRez, gameData.yCamRez);
        this.rayCaster = new RayCaster(gameData, cameraData);
        this.gridManager = new GridManager();

        this.cameraData = new CameraData(gameData);
        this.camera = new Camera(gameData, cameraData);

        this.playerKeyInputs = new PlayKeyInputs(gameData, cameraData);
        this.addKeyListener(playerKeyInputs);

        playerMouseInputs = new PlayerMouseInputs(gameData, cameraData);
        this.addMouseListener(playerMouseInputs);
        this.addMouseMotionListener(playerMouseInputs);

        this.setPreferredSize(new Dimension(gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ));
        this.setFocusable(true);
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

        //draw the castedBlocks blocks
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
