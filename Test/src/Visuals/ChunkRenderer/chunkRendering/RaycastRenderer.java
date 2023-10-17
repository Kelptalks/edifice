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
    private World world = new World();


    public RaycastRenderer(int width, int height) {
        super(width, height, TYPE_4BYTE_ABGR_PRE);
        this.drawingManager = new GridDrawingManager(width, height);
        draw();
        rayCast(world);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Block manipulation
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     */
    public void rayCast(World world){
        //cycle through projection cords
        for (int xTriangle = 0; xTriangle < xCamRez; xTriangle++){
            for (int yTriangle = 0; yTriangle < yCamRez; yTriangle++){
                //Find the block that this triangle cord would be on
                //for traingle cor 0,0 it would be I think the current cam cor
                //with no modification

                //ray cast through cord until block != 0

                //draw the correct block face in that triangle

            }
        }
    }

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
    private int xCamRez = 5;
    private int yCamRez = 5;

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
