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

        //cycle through all blocks in a flat plane
        //each of these blocks will be used fo there top 2 triangles
        //the starting locaiton of each cast will take place
        //on top of each triangle


        for (int y = 0; y < xCamRez; y++){
           for (int x = 0; x < yCamRez; x++){

               //This draws a block top based off 2 triangles inputs
               drawingManager.drawTopBlock(x+12, y-14, pathLeftTop(x, y), pathRightTop(x, y));
           }
        }
        System.out.println(world.getBlock(-6, -6, -14));
    }

    private int[] pathLeftTop(int x, int y){
        int z = 0;
        int block = world.getBlock(x, y, z);
        for (int distance = 0; distance < drawDistance; distance++)
        {
            x--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                return new int[]{block, 4};
            }

            y--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                return new int[]{block, 2};
            }

            z--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                return new int[]{block, 0};
            }
        }
        return new int[]{1, 0};
    }

    private int[] pathRightTop(int x, int y){
        int z = 0;
        int block = world.getBlock(x, y, z);
        for (int distance = 0; distance < drawDistance; distance++)
        {
            y--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                return new int[]{block, 1};
            }

            x--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                return new int[]{block, 5};
            }

            z--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                return new int[]{block, 3};
            }
        }
        return new int[]{1, 3};
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
    private int drawDistance = 50;

    //Render Size
    private int xCamRez = 30;
    private int yCamRez = 30;

    //Cam location
    private long xCamCor = 0;
    private long yCamCor = 0;
    private long zCamCor = 0;

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
