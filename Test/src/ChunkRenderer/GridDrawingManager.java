package ChunkRenderer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.w3c.dom.Text;
import Constants.Constants;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Grid Manager class
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*This class is responsable for drawing specific textures to the grid
*based off there face type.
*
*/
public class GridDrawingManager extends BufferedImage{
    Graphics2D graphics = this.createGraphics();
    TextureManager textureManager;

    GridDrawingManager(){
        super(Constants.SCREEN_X_REZ, Constants.SCREEN_Y_REZ, TYPE_4BYTE_ABGR_PRE);
        System.out.println("Created GridManager");
        this.textureManager = new TextureManager();
    }


/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Drawing methods
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*Methods for drawing triangles at specific locations
*/

    public void drawTriangle(int x, int y, BufferedImage blockFace){
        graphics.drawImage(blockFace, (x)*32, y*16, null);
    }

    public void drawBlock(int xCor, int yCor, int blockType){
        BufferedImage texture = null;
        try {
        // import the file.
        texture = ImageIO.read(new File("src\\ChunkRenderer\\Textures\\Grass.png"));

        } catch (IOException e) {
            // Handle the exception
            System.out.println("Block Texture failed to load.");
        }
        
        if (texture != null){
            graphics.drawImage(texture, (32*((xCor)-yCor)), ((16*((yCor)+xCor))), null);
        }
    }

    public void drawTriangle(int x, int y, int block, int face){
        //Convert block cords to num cords
        int xDrawCords = (32*(x-y));
        int yDrawCords = (16*(y+x));

        //Convert drawing cords based off triangles location
        if(face == 0){

        }
        else if(face == 1){
            yDrawCords+=16;
        }
        else if(face == 2){
            yDrawCords+=32;
        }
        else if(face == 3){
            xDrawCords+=32;
        }
        else if(face == 4){
            xDrawCords+=32;
            yDrawCords+=16;
        }
        else if(face == 5){
            xDrawCords+=32;
            yDrawCords+=32;
        }
        graphics.drawImage(textureManager.getFaceTexture(block, face), null, xDrawCords+32, yDrawCords+32);
    }

    public void testDrawTriangleGrid(BufferedImage triangle){
        Graphics2D g2d = this.createGraphics();
        g2d.setColor(Color.BLACK);

        for(int x = 0; x<60; x++){
            g2d.drawLine(x*32, 0, x*32, 1080);

            int offset = 0;
            if(x%2 == 0){
                offset = 16;
            }
            else{
                offset = 0;
            }
            for(int y = 0; y<34; y++){
                g2d.drawImage(triangle, x*32, y*32+offset, null);
            }
        }
    }
}
