package Visuals.ChunkRenderer.chunkRendering;

import World.Octree.Octree;
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
    //private World world = new World();


    public RaycastRenderer(int width, int height) {
        super(width, height, TYPE_4BYTE_ABGR_PRE);
        this.drawingManager = new GridDrawingManager(width, height);


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

                //get details
                int blockX = relativeBlockCor[0];
                int blockY = relativeBlockCor[1];
                boolean leftFacing = isLeftFacing(xTriangle, yTriangle);



            }
        }
    }

    public boolean isLeftFacing(int xTriangleCor, int yTriangleCor){
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
    public int[] tryCorToBlockCor(int xTriangleCor, int yTriangleCor){
        int blockXCor = (xTriangleCor/2) + (yTriangleCor/2);
        int blockYCor = (yTriangleCor/2) - (xTriangleCor/2);

        System.out.println("Block cords are " + blockXCor + " | " + blockYCor);

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
    //Render Size
    private int xCamRez = 20;
    private int yCamRez = 20;

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
