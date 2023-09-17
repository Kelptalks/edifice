package GameFrame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.BasicStroke;
import Constants.Constants;
import GameFrame.Textures.Textures;
import World.Chunk;

/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  BlockRender
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Used to draw blocks on the lowest level.
 * 
 * Plans : I need to be able to draw all faces of blocks individualy. This will make rendering faster and 3D easyer to implement in future.
 * 
 */

class BlockRender extends BufferedImage{
    //Used to import and mannage Textures
    private Textures textures = new Textures();
    private static final int BLOCK_WIDTH_FACTOR = 32;
    private static final int BLOCK_HEIGHT_FACTOR = 32/2;


    private static final int IMAGE_WIDTH = 1920;
    private static final int IMAGE_HEIGHT = 1080;

    private static final int CHUNK_DIMENSION = 16;

    

    /*
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Constructor
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *
    */


    BlockRender(){
        super(IMAGE_WIDTH, IMAGE_HEIGHT, TYPE_4BYTE_ABGR_PRE);
        //testDrawTriangleGrid();
        //testFaces();
        
    }


    /*
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Basic Drawing Functions
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */

    //draws a block texture at cords
    private void drawBlock(int xCor, int yCor, int blockType){
        Graphics2D g2d = this.createGraphics();
        BufferedImage texture = textures.getBlock(blockType);
        
        if (!(texture == null)){
            g2d.drawImage(texture, (BLOCK_WIDTH_FACTOR*((xCor)-yCor)), ((BLOCK_HEIGHT_FACTOR*((yCor)+xCor))), null);
            g2d.dispose();
        }
    }

    //Draws one lare of a chunk input array need int[16*16]
    public void drawLayer(int[] layer, int xCor, int yCor){
        for(int y = 0; y<CHUNK_DIMENSION; y++){
            for(int x = 0; x<CHUNK_DIMENSION; x++){
                drawBlock(xCor+x, yCor+y, layer[x + y*CHUNK_DIMENSION]);
            }
        }
    }

    //Draws one lare of a chunk input array need int[16*16] and factors in zCor
    public void drawLayer(int[] layer, int xCor, int yCor, int zCor){
        for(int y = 0; y<CHUNK_DIMENSION; y++){
            for(int x = 0; x<CHUNK_DIMENSION; x++){
                drawBlock(xCor+x-zCor, yCor+y-zCor, layer[x + y*CHUNK_DIMENSION]);
            }
        }
    }

    //Draws a chunk using the drawblock command, need to creat better function that draws using draw layer
    public void drawChunk(int[] chunkData, int xCor, int yCor){
        //iterate through every block in the chunk and draw it
        for(int z = 0; z<CHUNK_DIMENSION; z++){
            for(int y = 0; y<CHUNK_DIMENSION; y++){
                for(int x = 0; x<CHUNK_DIMENSION; x++){
                    drawBlock(xCor+x-z, yCor+y-z, chunkData[x + y*CHUNK_DIMENSION + z*CHUNK_DIMENSION*CHUNK_DIMENSION]);
                }
            }
        }
    }

    /*
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Depth based Drawing
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */

    //                          Lengths   32     63            2
    //Creates an 3D array that contains [xCor] [yCor] [BlockType - Face]
    public void depthDrawChunk(int[] chunkData, int xCor, int yCor){
        int chunkTriangleWidth = 32;
        int chunkTriangleHeight = 64;


        int projectChunkXRez = 49;
        int projectChunkYRez = 64;
        int[][][] projectedChunk = new int[projectChunkXRez][projectChunkYRez][2];
        int[][] depthMap = new int[49][64];

        //Create an array that fits the triangle grid sise for the chunk, 
        //the side of a perfect array would be 1536, but converting array cords to triangle grid cords is a bit tricky.
        //as the shape of the isometric cube resembles a 5 sided hexogon.

        //first, I will start with a normal 2D array, as that will simplify the prosess
        //iterate through every block in the chunk and draw it
        for(int z = 0; z<CHUNK_DIMENSION; z++){
            for(int y = 0; y<CHUNK_DIMENSION; y++){
                for(int x = 0; x<CHUNK_DIMENSION; x++){
                    //eliminate empty blocks from being checked
                    if (chunkData[x + y*16 + z*16*16] !=0){
                        //get block x, y drawing cord
                        
                        int xdrawingCord = (x-z);
                        int ydrawingCord = (y-z);

                        //do calculations to turn (x, y) drawing cords into (x, y) triangle drawing cords
                        
                        int blockDepth = x+y+z;


                        //test every triangle in the block
                        for (int triangle = 0; triangle<6; triangle++){

                            int yTriangleMod = 0;
                            int xTriangleMod = 0;

                            //lefttop
                            if(triangle == 0){
                                yTriangleMod--;
                            }

                            //topleft
                            if(triangle == 1){
                                xTriangleMod++;
                            }
                            //bottom left
                            if(triangle == 2){
                                yTriangleMod++;
                            }


                            //right top
                            if(triangle == 3){
                                xTriangleMod++;
                                yTriangleMod--;
                            }

                            //top right
                            if(triangle == 4){
                                xTriangleMod++;
                                yTriangleMod++;
                                
                            }


                            int xProjectedCord = (xdrawingCord-ydrawingCord+xTriangleMod)+32;
                            int yProjectedCord = (xdrawingCord+ydrawingCord+yTriangleMod)+32;

                            if (xProjectedCord<0){
                                xProjectedCord = Math.abs(xProjectedCord);
                            }
                            if (yProjectedCord<0){
                                xProjectedCord = Math.abs(xProjectedCord);
                            }

                            //testDrawTriangle(xProjectedCord, yProjectedCord, z);
                            
                            if( ((projectedChunk[xProjectedCord][yProjectedCord][0]) == 0) || (depthMap[xProjectedCord][yProjectedCord] < blockDepth) ){
                                (projectedChunk[xProjectedCord][yProjectedCord][0]) = chunkData[x + y*16 + z*16*16];
                                (projectedChunk[xProjectedCord][yProjectedCord][1]) = triangle;
                            }
                        }
                    }
                }
            }
        }

        for(int y = 0; y<projectChunkYRez; y++){
            for(int x = 0; x<projectChunkXRez; x++){
                drawTriangle(x+16*xCor, y+16*yCor, projectedChunk[x][y][0], projectedChunk[x][y][1]);
            }
        }

    }

    private void drawTriangle(int x, int y, int blockType, int face){
        Graphics2D g2d = this.createGraphics();
        if (face < 3){
            g2d.drawImage(textures.getBlockFace(blockType, face), x*BLOCK_WIDTH_FACTOR, y*BLOCK_HEIGHT_FACTOR, null);
        }
        else{
            g2d.drawImage(textures.getBlockFace(blockType, face), x*BLOCK_WIDTH_FACTOR, y*BLOCK_HEIGHT_FACTOR, null);
        }
        g2d.dispose();
    }
    /*
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Debugging 
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */

    //draw the grid in whitch triangles inhabit
    public void testDrawTriangleGrid(){
        Graphics2D g2d = this.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3.0f));

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
                g2d.drawImage(textures.getBlockFace(2,0), x*32, y*32+offset, null);
            }
        }
    }

    //draw a chunk with blocks of diffrent depths as diffrent block types.
    private void testDepthMap(){
        for(int z = 0; z<CHUNK_DIMENSION; z++){
            for(int y = 0; y<CHUNK_DIMENSION; y++){
                for(int x = 0; x<CHUNK_DIMENSION; x++){
                    int projectedYcor = x - z;
                    int projectedXcor = y - z;
                    int blockDepth = x + y + z;

                    if (blockDepth == 0){
                        drawBlock(projectedXcor, projectedYcor, 16);
                    }
                    else if (blockDepth <=7){
                        drawBlock(projectedXcor, projectedYcor, 1);
                    }
                    else if (blockDepth <=14){
                        drawBlock(projectedXcor, projectedYcor, 2);
                    }
                    else if (blockDepth <=21){
                        drawBlock(projectedXcor, projectedYcor, 3);
                    }
                    else if (blockDepth <=28){
                        drawBlock(projectedXcor, projectedYcor, 4);
                    }
                    else if (blockDepth <=35){
                        drawBlock(projectedXcor, projectedYcor, 5);
                    }
                    else if (blockDepth <=42){
                        drawBlock(projectedXcor, projectedYcor, 6);
                    }
                    else if (blockDepth <=49){
                        drawBlock(projectedXcor, projectedYcor, 7);
                    }
                    else if (blockDepth <=56){
                        drawBlock(projectedXcor, projectedYcor, 8);
                    }
                }
            }
        }
    }

    //draws a 16 by 16 flat plane
    private void testDraw(){
        for (int y = -8; y<8; y++){
            for (int x = -8; x<8; x++){
                //Draws the center with grass
                if((x == 0 & y==0) || (x == -1 & y==-1) || (x == -1 & y==0) || (x == 0 & y==-1)){
                    drawBlock(x, y, 0);
                }
                else{drawBlock(x, y, 1);}
            }
        }
    }

    //Basic drawing function to test drawing and adding to Jframe
    private void testDrawBox(){
        Graphics2D g2d = this.createGraphics();
        g2d.setColor(Color.RED);
        g2d.drawRect(1, 1, 50, 50);
        g2d.dispose();
    }

    //Draws all face textures and a combine texture, to test clipping.
    private void testFaces(){
        Graphics2D g2d = this.createGraphics();
        int scaleFactor = 1;

        //Need to solve so I don't have to cast.
        for (int x = 0; x<6; x++){
            BufferedImage image = (BufferedImage) textures.getBlockFace(9,x);
            g2d.drawImage(scaleImage(image, scaleFactor), 50+x*200, 50, null);
        }

        //put the block together again with spliced faces
        BufferedImage image = (BufferedImage) textures.getBlockFace(1,0);
        g2d.drawImage(scaleImage(image, scaleFactor), 50+BLOCK_WIDTH_FACTOR*scaleFactor, 500, null);

        image = (BufferedImage) textures.getBlockFace(1,1);
        g2d.drawImage(scaleImage(image, scaleFactor), 50+BLOCK_WIDTH_FACTOR*scaleFactor*2, 500, null);

        image = (BufferedImage) textures.getBlockFace(1,2);
        g2d.drawImage(scaleImage(image, scaleFactor), 50+BLOCK_WIDTH_FACTOR*scaleFactor, 500+BLOCK_HEIGHT_FACTOR*scaleFactor, null);

        image = (BufferedImage) textures.getBlockFace(1,3);
        g2d.drawImage(scaleImage(image, scaleFactor), 50+BLOCK_WIDTH_FACTOR*scaleFactor, 500+BLOCK_WIDTH_FACTOR*scaleFactor, null);

        image = (BufferedImage) textures.getBlockFace(1,4);
        g2d.drawImage(scaleImage(image, scaleFactor), 50+BLOCK_WIDTH_FACTOR*scaleFactor*2, 500+BLOCK_HEIGHT_FACTOR*scaleFactor, null);

        image = (BufferedImage) textures.getBlockFace(1,5);
        g2d.drawImage(scaleImage(image, scaleFactor), 50+BLOCK_WIDTH_FACTOR*scaleFactor*2, 500+BLOCK_WIDTH_FACTOR*scaleFactor, null);

            
    }

    //Draws a triangle in a specific cord in the triangle grid
    private void testDrawTriangle(int x, int y, int blockType){
        Graphics2D g2d = this.createGraphics();

        boolean isXOdd = (x & 1) != 0;
        boolean isYOdd = (y & 1) != 0;


        //Triangle placement reverses if x is odd
        if (!isXOdd){
            //Triangle placment reverses if y is odd
            if(!isYOdd){
                g2d.drawImage(textures.getBlockFace(blockType, 1), x*BLOCK_WIDTH_FACTOR, y*BLOCK_HEIGHT_FACTOR, null);
            }
            else{
                g2d.drawImage(textures.getBlockFace(blockType, 0), x*BLOCK_WIDTH_FACTOR, y*BLOCK_HEIGHT_FACTOR, null);
            }
        }

        else{
            if(isYOdd){
                g2d.drawImage(textures.getBlockFace(blockType, 1), x*BLOCK_WIDTH_FACTOR, y*BLOCK_HEIGHT_FACTOR, null);
            }
            else{
                g2d.drawImage(textures.getBlockFace(blockType, 0), x*BLOCK_WIDTH_FACTOR, y*BLOCK_HEIGHT_FACTOR, null);
            }

        }

        g2d.dispose();
    }

    //I did not write this, but use for testing
    private BufferedImage scaleImage(BufferedImage srcImg, double factor) {
        int w = (int)(srcImg.getWidth(null) * factor);
        int h = (int)(srcImg.getHeight(null) * factor);
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }
    

}