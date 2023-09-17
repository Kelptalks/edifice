package GameFrame.Textures;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Texture class
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* This class is for loading and splicing textures
*/

public class Textures {
    BufferedImage []images = new BufferedImage[100];
    BufferedImage [][]blockFaces = new BufferedImage[100][6]; 

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Constructor
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * NEED TO ADD : Will load, splice, and orginise immages into an array
    */
    public Textures(){
        //adds the textures to there slots in the array
        images[1] = loadTexture("src\\GameFrame\\Textures\\Block.png");
        images[2] = loadTexture("src\\GameFrame\\Textures\\Block1.png");
        images[3] = loadTexture("src\\GameFrame\\Textures\\Block2.png");
        images[4] = loadTexture("src\\GameFrame\\Textures\\Block3.png");
        images[5] = loadTexture("src\\GameFrame\\Textures\\Block4.png");
        images[6] = loadTexture("src\\GameFrame\\Textures\\Block5.png");
        images[7] = loadTexture("src\\GameFrame\\Textures\\Block6.png");
        images[8] = loadTexture("src\\GameFrame\\Textures\\Block7.png");
        images[9] = loadTexture("src\\GameFrame\\Textures\\Grass.png");
        images[10] = loadTexture("src\\GameFrame\\Textures\\Blorb.png");

        for (int x = 1; x<11; x++){
            spliceAndStoreBlock(images[x], x);
        }

    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Importing and organising methods
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Methods for loading, returning, and organising textures
    */

    private BufferedImage loadTexture(String filePath){
        try {
        // Read the image file from the filesystem.
        BufferedImage test = ImageIO.read(new File(filePath));

        // Return image
        System.out.println("Picture Imported : " + filePath);

        return test;

        } catch (IOException e) {
            // Handle the exception
            System.out.println("Picture failed to import");
            return null;
        }
    }

    public void spliceAndStoreBlock(BufferedImage blockTexture, int blockType){
        blockFaces[blockType][0] = spliceTopFaceLeft(blockTexture);
        blockFaces[blockType][1] = spliceRightFaceTop(blockTexture);
        blockFaces[blockType][2] = spliceLeftFaceBottom(blockTexture);


        blockFaces[blockType][3] = spliceTopFaceRight(blockTexture);
        blockFaces[blockType][4] = spliceRightFaceBottom(blockTexture);
        blockFaces[blockType][5] = spliceRightFaceBottom(blockTexture);

        System.out.println("Spliced and stored block : " + blockType);
    }

    //returns the blockface of a specific block.
    public BufferedImage getBlockFace(int blockType, int blockFace){
        return blockFaces[blockType][blockFace];
    }   

    //Returns a block texture based of the block
    public BufferedImage getBlock(int blockType){
        return images[blockType];
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Block Face Splicing Methods
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Methods for splicing a block into the 6 possible grid textures
    */

    //splices top left 
    private BufferedImage spliceTopFaceLeft(BufferedImage blockTexture){
        //Create blank to draw splice too.
        BufferedImage newImage = new BufferedImage(64, 32, BufferedImage.TYPE_4BYTE_ABGR);

        //Peramiters for isolating a general rectangular area of the splice
        int startY = 0;
        int endY = 32;
        int startX = 0;
        int endX = 32;

        //Loop for iterating through the isolated rectangular area.
        for (int y = startY; y < endY; y++) {
            for (int x = startX; x < endX; x++) {
                int pixel = blockTexture.getRGB(x, y);

                //Condition to avoid adding unwanted pixels
                if ((y >= 16 + x / 2) || (y >= 15 - (x / 2) + 32)) {
                    continue; // Skip the rest of the code in the loop
                }
                newImage.setRGB(x - startX, y - startY, pixel);
            }
        }
    return newImage;
    }

    //splices top right
    private BufferedImage spliceTopFaceRight(BufferedImage blockTexture){
        //Create blank to draw splice too.
        BufferedImage newImage = new BufferedImage(64, 32, BufferedImage.TYPE_4BYTE_ABGR);

        //Peramiters for isolating a general rectangular area of the splice
        int startY = 0;
        int endY = 32;
        int startX = 32;
        int endX = 64;

        //Loop for iterating through the isolated rectangular area.
        for (int y = startY; y < endY; y++) {
            for (int x = startX; x < endX; x++) {
                int pixel = blockTexture.getRGB(x, y);

                //Condition to avoid adding unwanted pixels
                if ((y >= 16 + x / 2) || (y >= 15 - (x / 2) + 32)) {
                    continue; // Skip the rest of the code in the loop
                }
                newImage.setRGB(x - startX, y - startY, pixel);
            }
        }
        return newImage;
    }

    //splices left top
    private BufferedImage spliceLeftFaceTop(BufferedImage blockTexture){
        //Create blank to draw splice too.
        BufferedImage newImage = new BufferedImage(32, 33, BufferedImage.TYPE_4BYTE_ABGR);
        //Peramiters for isolating a general rectangular area of the splice
        int startY = 15;
        int endY = 64;
        int startX = 0;
        int endX = 32;

         //Loop for iterating through the isolated rectangular area.
        for (int y = startY; y < endY; y++) {
            for (int x = startX; x < endX; x++) {
                int pixel = blockTexture.getRGB(x, y);
                
                if((y<15+x/2) || (y >= 15 - (x / 2) + 32)){
                    continue; // Skip the rest of the code in the loop
                }

                newImage.setRGB(x - startX, y - startY, pixel);
            }
        }
        return newImage;
    }

    //splices left bottom
    private BufferedImage spliceLeftFaceBottom(BufferedImage blockTexture){
        //Create blank to draw splice too.
        BufferedImage newImage = new BufferedImage(32, 33, BufferedImage.TYPE_4BYTE_ABGR);
        //Peramiters for isolating a general rectangular area of the splice
        int startY = 31;
        int endY = 64;
        int startX = 0;
        int endX = 32;

         //Loop for iterating through the isolated rectangular area.
        for (int y = startY; y < endY; y++) {
            for (int x = startX; x < endX; x++) {
                int pixel = blockTexture.getRGB(x, y);
                
                if(y < 14 - (x / 2) + 32 || y >= 49 + x / 2){
                    continue; // Skip the rest of the code in the loop
                }
                newImage.setRGB(x - startX, y - startY, pixel);
            }
        }
        return newImage;
    }

    //splices right top
    private BufferedImage spliceRightFaceTop(BufferedImage blockTexture){
        //Create blank to draw splice too.
        BufferedImage newImage = new BufferedImage(32, 32, BufferedImage.TYPE_4BYTE_ABGR);        

        //Peramiters for isolating a general rectangular area of the splice
        int startY = 15;
        int endY = 48;
        int startX = 32;
        int endX = 64;

         //Loop for iterating through the isolated rectangular area.
        for (int y = startY; y < endY; y++) {
            for (int x = startX; x < endX; x++) {
                int pixel = blockTexture.getRGB(x, y);

                if((y >= 16 + x / 2) || (y < 14 - (x / 2) + 32)){
                    continue;
                }

                newImage.setRGB(x - startX, y - startY, pixel);
            }
        }
        return newImage;
    }

    //splices right bottom
    private BufferedImage spliceRightFaceBottom(BufferedImage blockTexture){
        //Create blank to draw splice too.
        BufferedImage newImage = new BufferedImage(32, 33, BufferedImage.TYPE_4BYTE_ABGR);        

        //Peramiters for isolating a general rectangular area of the splice
        int startY = 31;
        int endY = 64;
        int startX = 32;
        int endX = 64;

         //Loop for iterating through the isolated rectangular area.
        for (int y = startY; y < endY; y++) {
            for (int x = startX; x < endX; x++) {
                int pixel = blockTexture.getRGB(x, y);

                if((y<15+x/2) || (y < 14 - (x / 2) + 32)){
                    continue;
                }
                newImage.setRGB(x - startX, y - startY, pixel);
            }
        }
        return newImage;
    }
    
    
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Debugging Methods
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Methods for splicing the faces out of a block texture
    */

   
}
