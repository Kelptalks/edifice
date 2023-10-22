package Visuals.raycastRendering;

import World.Blueprint.Blueprint;
import World.World;

import java.awt.*;
import java.awt.image.BufferedImage;
/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Block manipulation
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 */
public class RaycastRenderer extends BufferedImage {

    private GridDrawingManager drawingManager;
    private Graphics graphics = this.getGraphics();
    private Blueprint world = new Blueprint(1000, 1000, 1000);

    public RaycastRenderer(int width, int height) {
        super(width, height, TYPE_4BYTE_ABGR_PRE);
        this.drawingManager = new GridDrawingManager(width, height);
        world.setBlock(100, 0 ,-1, 4);

        System.out.println(world.getBlock(100,0, -1));
        System.out.println(world.getBlock(101,0, -1));

        rayCast();

        graphics.drawImage(drawingManager, 0, 0, null);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Ray casting
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */
    public void rayCast(){

        //cycle through projection cords
        for (int xTriangle = 0; xTriangle < xCamRez; xTriangle++){
            for (int yTriangle = 0; yTriangle < yCamRez; yTriangle++){
                int[] relativeBlockCor = tryCorToBlockCor(xTriangle,yTriangle);

                //get the block Cords based off the triangle.
                long blockX = relativeBlockCor[0] + xCamCor;
                long blockY = relativeBlockCor[1] + yCamCor;
                long blockZ = zCamCor;

                //Get What direction the triangle is facing based of the cords.
                boolean leftFacing = isLeftFacing(xTriangle, yTriangle);

                // cycle through all blocks that could possibly fill that triangle
                // and break and draw the first non-translucent block.
                int[] castedBlock = castBlockCor(xTriangle, yTriangle, zCamCor, leftFacing);
                if (castedBlock != null){
                    drawingManager.drawTriangle(xTriangle, yTriangle, castedBlock[0], castedBlock[1]);
                }
            }
        }
    }

    private int[] castBlockCor(long blockX, long blockY, long blockZ, boolean leftFacing){
        int transparent = 0;
        if (leftFacing){
            for (int distance = 0; distance < drawDistance; distance++) {
                //x-1
                blockX --;
                if (world.getBlock(blockX, blockY, blockZ) != transparent){
                    //return the blockType and face type
                    //System.out.println("Traversal distance : " + distance);
                    return new int[]{world.getBlock(blockX, blockY, blockZ), 4};
                };

                //y-1
                blockY --;
                if (world.getBlock(blockX, blockY, blockZ) != transparent){
                    //return the blockType and face type
                    return new int[]{world.getBlock(blockX, blockY, blockZ), 2};
                };

                //z-1
                blockZ--;
                if (world.getBlock(blockX, blockY, blockZ) != transparent){
                    //return the blockType and face type
                    //System.out.println("Traversal distance : " + distance);
                    return new int[]{world.getBlock(blockX, blockY, blockZ), 0};
                };
            }
        }

        //if right facing
        else{
            for (int distance = 0; distance < drawDistance; distance++) {
                //y-1
                blockY--;
                if (world.getBlock(blockX, blockY, blockZ) != transparent) {
                    //return the blockType and face type
                    return new int[]{world.getBlock(blockX, blockY, blockZ), 5};
                }

                //x-1
                blockX --;
                if (world.getBlock(blockX, blockY, blockZ) != transparent){
                    //return the blockType and face type
                    //System.out.println("Traversal distance : " + distance);
                    return new int[]{world.getBlock(blockX, blockY, blockZ), 1};
                };

                //z-1
                blockZ--;
                if (world.getBlock(blockX, blockY, blockZ) != transparent){
                    //return the blockType and face type
                    return new int[]{world.getBlock(blockX, blockY, blockZ), 3};
                };
            }
        }
        return null;
    }

    private boolean isLeftFacing(int xTriangleCor, int yTriangleCor){
        //Identify the face orientation
        boolean leftFacing = false;
        if (xTriangleCor % 2 == 0){
            if (yTriangleCor % 2 == 0){
                leftFacing = true;
            }
            else {
                leftFacing = false;
            }
        }
        else {
            if (yTriangleCor % 2 == 0){
                leftFacing = false;
            }
            else {
                leftFacing = true;
            }
        }
        return leftFacing;
    }

    //Takes in triangle cords and returns the relative block location
    private int[] tryCorToBlockCor(int xTriangleCor, int yTriangleCor){
        int blockXCor = (xTriangleCor/2) + (yTriangleCor/2);
        int blockYCor = (yTriangleCor/2) - (xTriangleCor/2);

        //drawingManager.drawTriangle(blockXCor+5, blockYCor+10, 1, 0);

        return new int[]{blockXCor, blockYCor};
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Drawing
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */
    public void draw(){
        drawingManager.testDrawTriangleGrid();
        drawingManager.drawTriangle(+5, +5, 1, 2);
        graphics.drawImage(drawingManager, 0, 0, null);
    }



    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Cam Control
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */

    //draw distance
    private int drawDistance = 1000;

    //Render Size
    private int xCamRez = 50;
    private int yCamRez = 50;

    //Cam location
    private long xCamCor = 20;
    private long yCamCor = 20;
    private long zCamCor = 20;

    private long getXCamCor(){
        return this.xCamCor;
    }

    private long getYCamCor(){
        return this.yCamCor;
    }

    private long getZCamCor(){
        return this.zCamCor;
    }
}
