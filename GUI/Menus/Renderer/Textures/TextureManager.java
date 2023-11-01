package GUI.Menus.Renderer.Textures;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.BiPredicate;

import javax.imageio.ImageIO;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Texture class
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* Creates spliced and shaded versions of each block and stores them.
*/
public class TextureManager {

    private BufferedImage [][]blockTextures = new BufferedImage[15][6];
    private String blockFilePaths[] = {null, "GUI/Menus/Renderer/Textures/Grass.png", "GUI/Menus/Renderer/Textures/Block.png", "GUI/Menus/Renderer/Textures/Sand.png"
    , "GUI/Menus/Renderer/Textures/Blorb.png", "GUI/Menus/Renderer/Textures/Debug.png"};

    public TextureManager(){
        spliceAndStore(1);
        spliceAndStore(2);
        spliceAndStore(3);
        spliceAndStore(4);
        spliceAndStore(5);
    }

    public BufferedImage[] getBlockTexture(int blockType){
        return blockTextures[blockType];
    }

    public BufferedImage getFaceTexture(int blockType, int blockFace){
        return blockTextures[blockType][blockFace];
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Loading and storing textures
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Store textures into an array corasponding with blocktype.
    *
    * |BLOCK ARRAY STORAGE|
    *   Explanation : Blocks are stored in a 3d array with each dimention corasponding to a more specific version of the block
    *   Dimentions :
    *       1.[] corasponds to the block type
    *       2.[][] corasponds to the block face
    *       3.[][][] Corraspons to block shader used for shadows
    */

    private void spliceAndStore(int blockType){
        blockTextures[blockType] = splicedBlock(loadTexture(blockType));
    }

    private BufferedImage loadTexture(int blockType){
        String filePath = blockFilePaths[blockType]; 
        try {
        // import the file.
        BufferedImage test = ImageIO.read(new File(filePath));

        // Return image
        System.out.println("Picture Imported (" + filePath + ")");

        return test;

        } catch (IOException e) {
            // Handle the exception
            System.out.println("Block Texture failed to load.| Block Type(" + blockType + ") | File path(" + filePath + ")");
            return null;
        }
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Splicing textures
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Splice Textures into there 6 diffrent triangles
    */

    private BufferedImage[] splicedBlock(BufferedImage blockTexture){
        BufferedImage splices[] = new BufferedImage[6];

        splices[0] = splice(blockTexture, 0, 32, 0, 32, (x, y) -> (y >= 16 + x / 2) || (y >= 15 - (x / 2) + 32));//Left top
        splices[1] = splice(blockTexture, 0, 32, 0, 64, (x, y) -> (y<15+x/2) || (y >= 15 - (x / 2) + 32));//Left middle
        splices[2] = splice(blockTexture, 0, 32, 32, 64, (x, y) -> (y < 14 - (x / 2) + 32 || y >= 49 + x / 2));//Left bottom
        splices[3] = splice(blockTexture, 32, 64, 0, 32, (x, y) -> (y >= 16 + x / 2) || (y >= 15 - (x / 2) + 32));//Right top
        splices[4] = splice(shade(blockTexture), 32, 64, 15, 48, (x, y) -> (y >= 16 + x / 2) || (y < 14 - (x / 2) + 32));//Right middle
        splices[5] = splice(blockTexture, 32, 64, 31, 64, (x, y) -> (y < 15 + x / 2) || (y < 16 - (x / 2) + 32));//Right bottom


        return splices;
    }

    public BufferedImage splice(BufferedImage blockTexture, int startX, int endX, int startY, int endY, BiPredicate<Integer, Integer> condition){
        //Create blank to draw splice too.
        BufferedImage newImage = new BufferedImage(64, 64, BufferedImage.TYPE_4BYTE_ABGR);

        //Loop for iterating through the isolated rectangular area.
        for (int y = startY; y < endY; y++) {
            for (int x = startX; x < endX; x++) {
                int pixel = blockTexture.getRGB(x, y);//get the color of a single pixel

                //Use inputed condition to skip adding unwanted pixels
                if (!condition.test(x, y)) {
                    newImage.setRGB(x - startX, y - startY, pixel);//set the color of a single pixel
                }
            }
        }
        return trimAndResize(newImage);
    }

    public BufferedImage trimAndResize(BufferedImage toTrim){
        int minX = toTrim.getWidth(), minY = toTrim.getHeight(), maxX = 0, maxY = 0;

        for (int y = 0; y < toTrim.getHeight(); y++) {
            for (int x = 0; x < toTrim.getWidth(); x++) {
                // Remove transparent pixels
                if ((toTrim.getRGB(x, y) != 0x00000000)) {
                    if (x < minX) minX = x;
                    if (y < minY) minY = y;
                    if (x > maxX) maxX = x;
                    if (y > maxY) maxY = y;
                }
            }
        }
        return toTrim.getSubimage(minX, minY, maxX - minX + 1, maxY - minY + 1);
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

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Mod textures
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Modify tetures to add diffrent shades.
    */


}
