import Constants.Constants;
import Controls.KeyBoardInputs;
import Controls.MouseInputs;
import Testing.PackageTests.RenderTesting;
import Visuals.ChunkRenderer.ViewPort;
import Visuals.ChunkRenderer.chunkRendering.RaycastRenderer;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.awt.image.BufferedImage.TYPE_4BYTE_ABGR_PRE;

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

        this.add(viewPort);
    }

}