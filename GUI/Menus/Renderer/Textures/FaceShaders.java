package GUI.Menus.Renderer.Textures;

import java.awt.*;
import java.awt.image.BufferedImage;
/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Face shader
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Adds shaded textures to blocks
 *
 */
public class FaceShaders {
    public FaceShaders(){

    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Face shader
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Adds shaded textures to blocks
     *
     */

    //shades the top face
    public BufferedImage shadeTopFaceTop(BufferedImage face){
        BufferedImage shadedFace = new BufferedImage(face.getWidth(), face.getHeight(), face.getType());
        Graphics2D g2d = shadedFace.createGraphics();
        g2d.drawImage(face, 0, 0, null);
        // Set the color to a semi-transparent black
        g2d.setColor(new Color(0x7C000000, true));

        //Use inputted condition to skip adding unwanted pixels
        for (int y = 0; y< shadedFace.getHeight()/2; y++){
            for(int x = 0; x< shadedFace.getWidth(); x++){
                if((shadedFace.getRGB(x, y) != 0x00000000)){
                    g2d.fillRect(x, y, 1, 1);
                }
            }
        }
        g2d.dispose();
        return shadedFace;
    }

    //shades the top face
    public BufferedImage shadeTopFaceBottom(BufferedImage face){
        BufferedImage shadedFace = new BufferedImage(face.getWidth(), face.getHeight(), face.getType());
        Graphics2D g2d = shadedFace.createGraphics();
        g2d.drawImage(face, 0, 0, null);
        // Set the color to a semi-transparent black
        g2d.setColor(new Color(0x7C000000, true));

        //Use inputted condition to skip adding unwanted pixels
        for (int y = shadedFace.getHeight()/2; y < shadedFace.getHeight(); y++){
            for(int x = 0; x< shadedFace.getWidth(); x++){
                if((shadedFace.getRGB(x, y) != 0x00000000)){
                    g2d.fillRect(x, y, 1, 1);
                }
            }
        }
        g2d.dispose();
        return shadedFace;
    }

    //shades the entire block.
    public BufferedImage shadeWhole(BufferedImage face){
        BufferedImage shadedFace = new BufferedImage(face.getWidth(), face.getHeight(), face.getType());
        Graphics2D g2d = shadedFace.createGraphics();
        g2d.drawImage(face, 0, 0, null);
        // Set the color to a semi-transparent black
        g2d.setColor(new Color(0x7C000000, true));

        //Use inputted condition to skip adding unwanted pixels
        for (int y = 0; y< shadedFace.getHeight(); y++){
            for(int x = 0; x< shadedFace.getWidth(); x++){
                if((shadedFace.getRGB(x, y) != 0x00000000)){
                    g2d.fillRect(x, y, 1, 1);
                }
            }
        }
        g2d.dispose();
        return shadedFace;
    }
}
