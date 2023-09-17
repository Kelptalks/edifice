

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import ChunkRenderer.ChunkRenderer;
import ChunkRenderer.GridDrawingManager;
import Constants.Constants;
import World.Chunk;

public class GameFrame extends JFrame{

    Constants constants;
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Constructor
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public ChunkRenderer chunkRenderer;
    public GameFrame() throws IOException{
        super();
        this.setSize(Constants.SCREEN_X_REZ, Constants.SCREEN_Y_REZ);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Telp");
        setIcon();
        System.out.println("Created JFrame");

        this.chunkRenderer = new ChunkRenderer();
        
        this.add(chunkRenderer);
        this.setVisible(true);
    }

    private void setIcon() throws IOException{
        //this.setIconImage(ImageIO.read(new File("src\\ChunkRenderer\\Textures\\block.png")));
    }

}
