package GUI.Menus.Renderer.Textures;

import java.awt.*;
import java.awt.image.BufferedImage;
/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Face shader
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Adds shaded textures to blocks
 *
 * I'm going to need to make 12 different types
 *
 */
public class FaceShaders {
    private BufferedImage[][] shades;
    public FaceShaders(){
        shades = new BufferedImage[2][5];
        createShadedTopFace();
        createShadedLeftFace();
    }

    private void createShadedLeftFace() {
        //top
        int[] xPoints = new int[]{0, 0, 16};
        int[] yPoints = new int[]{0, 32, 24};
        shades[0][3] = drawShadedPolygon(xPoints, yPoints);
        xPoints = new int[]{0, 16, 32};
        yPoints = new int[]{32, 24, 48};
        shades[0][4] = drawShadedPolygon(xPoints, yPoints);
        //bot
        xPoints = new int[]{32, 16, 0};
        yPoints = new int[]{16, 24, 0};
        shades[1][3] = drawShadedPolygon(xPoints, yPoints);
        xPoints = new int[]{32, 16, 0};
        yPoints = new int[]{48, 24, 32};
        shades[1][4] = drawShadedPolygon(xPoints, yPoints);
    }


    public void createShadedTopFace(){
        //top
        int[] xPoints = new int[]{0, 32, 32};
        int[] yPoints = new int[]{16, 0, 16};
        shades[0][1] = drawShadedPolygon(xPoints, yPoints);
        xPoints = new int[]{32, 0, 0};
        yPoints = new int[]{16, 0, 16};
        shades[0][2] = drawShadedPolygon(xPoints, yPoints);
        //bot
        xPoints = new int[]{0, 32, 32};
        yPoints = new int[]{16, 32, 16};
        shades[1][1] = drawShadedPolygon(xPoints, yPoints);

        xPoints = new int[]{0, 0, 32};
        yPoints = new int[]{16, 32, 16};
        shades[1][2] = drawShadedPolygon(xPoints, yPoints);
    }

    public BufferedImage drawShadedPolygon(int[] xPoints, int[] yPoints){
        BufferedImage image = new BufferedImage(64, 64, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics gTop = image.getGraphics();
        gTop.setColor(new Color(0xC0000000, true));
        gTop.fillPolygon(xPoints, yPoints, 3);
        return image;
    }

    public BufferedImage shade(BufferedImage image) {
        Graphics2D g2d = image.createGraphics();

        // Set the color to a semi-transparent black
        g2d.setColor(new Color(0x7C000000, true));

        //Use inputed condition to skip adding unwanted pixels
        for (int y = 0; y< image.getHeight(); y++){
            for(int x = 0; x< image.getWidth(); x++){
                if((image.getRGB(x, y) != 0x00000000)){
                    g2d.fillRect(x, y, 1, 1);
                }
            }
        }
        g2d.dispose();
        return image;
    }

    public BufferedImage getShader(int face, int type) {
        return shades[face][type];
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Create the different shades
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Shades are similar to the triangle grid,
     * but from a different angle.
     *
     * Triangles essentially rotated.
     *
     */


}
