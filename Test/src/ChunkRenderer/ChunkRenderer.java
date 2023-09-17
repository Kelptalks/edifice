package ChunkRenderer;
import java.awt.Graphics;

import javax.swing.JLabel;

import Constants.Constants;
import World.Chunk;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Chunk Renderer
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* Methods for eliminating unnessisary data for rendering 
*/
public class ChunkRenderer extends JLabel{
    private GridDrawingManager gridDrawingManager;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Constructor
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* Initilise low level grid drawing manager and Texture manager
*/

    public ChunkRenderer(){
        System.out.println("Created ChunkRenderer");

        this.gridDrawingManager = new GridDrawingManager();

        Chunk chunk = new Chunk();
        for (int z = 0; z<Constants.CHUNK_DIMENSION; z++){
            for (int y = 0; y<Constants.CHUNK_DIMENSION; y++){
                for (int x = 0; x<Constants.CHUNK_DIMENSION; x++){
                    chunk.setBlock(x,y,z,2);
                }
            }
        }

        for (int z = 4; z<16; z++){
            for (int y = 9; y<16; y++){
                for (int x = 8; x<16; x++){
                    chunk.setBlock(x,y,z,0);
                }
            }
        }
        for (int z = 1; z<12; z++){
            for (int y = 1; y<12; y++){
                for (int x = 1; x<10; x++){
                    chunk.setBlock(x,y,z,0);
                }
            }
        }
        //drawProjectedChunk(projectChunk(chunk.unpackChunk3D()), 13, -10);

        //projectChunkToTriangleGrid(chunk.unpackChunk3D());
        projectChunkToTriangleGrid(chunk.unpackChunk3D());
    }

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Rendering preperation
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* Methods for eliminating unnessisary data from the 3D chunk for rendering
*/

    private int[][] projectLeftChunkFace(){
        int[][] projectedFace = new int[32][32];
        return projectedFace;
    }

    //Converts a 3D chunk array into a projected 2D array by utilising a depth map to eliminate unseeable blocks
    private int[][] projectChunk(int[][][] chunkData){
        int[][] projectedArray = new int[32][32];
        int[][] depthMap = new int[32][32];
        int test = Constants.CHUNK_DIMENSION;

        for (int z = 0; z<Constants.CHUNK_DIMENSION; z++){
            for (int y = 0; y<Constants.CHUNK_DIMENSION; y++){
                for (int x = 0; x<Constants.CHUNK_DIMENSION; x++){
                    //convert 3D chunk cords to 2D drawing cords
                    int projectedXCor = (x-z)+15;
                    int projectedYcor = (y-z)+15;

                    int blockDepth = x+y+z;
                    if(chunkData[x][y][z] != 0){
                        if(depthMap[projectedXCor][projectedYcor] <= blockDepth){
                            projectedArray[projectedXCor][projectedYcor] = chunkData[x][y][z];
                        }
                    }
                }
            }
        }
        return projectedArray;
    }

    private int[][][] projectChunkToTriangleGrid(int[][][] chunkData){
        int[][][] projectedArray = new int[32][64][6];
        int[][] depthMap = new int[32][64];
        int test = Constants.CHUNK_DIMENSION;

        for (int z = 0; z<Constants.CHUNK_DIMENSION; z++){
            for (int y = 0; y<Constants.CHUNK_DIMENSION; y++){
                for (int x = 0; x<Constants.CHUNK_DIMENSION; x++){
                    //convert 3D chunk cords to 2D drawing cords and shift out of negatives
                    int projectedXCor = (x-z)+15;
                    int projectedYcor = (y-z)+15;

                    int blockDepth = x+y+z;
                    if(chunkData[x][y][z] != 0){
                        //Incrament through triangles block covers 1-6. 0-2 are left side and 2-5 are right side
                        for(int xChange = 0; xChange<2; xChange++){
                            for(int yChange = 0; yChange<3; yChange++){
                                //check if triangle is closer to camera
                                if(depthMap[x+xChange][y+yChange] < blockDepth){
                                    //replace 
                                    projectedArray[projectedXCor][projectedYcor][(xChange*3)+yChange] = chunkData[x][y][z];
                                    gridDrawingManager.drawTriangle(projectedXCor+15, projectedYcor-14, chunkData[x][y][z], (xChange*3)+yChange);
                                }
                            }
                        }
                    
                    }
                }
            }
        }
        return projectedArray;
    }

    //I need to project each chunk face onto a triangle grid. 32 by 32 triangles
/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Drawing
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* Methods for drawing chunks
*/

    public void drawProjectedChunk(int[][] projectChunk, int xCor, int yCor){
        for (int x = 0; x<projectChunk.length; x++){
            for (int y = 0; y<projectChunk[0].length; y++){
                if (projectChunk[x][y] != 0){
                    gridDrawingManager.drawBlock(x + xCor, y + yCor, projectChunk[x][y]);
                }
            }
        }
    }

    public void drawTriangleProjection(int[][][] projectedArray){
        for (int x = 0; x<projectedArray.length; x++){
            for (int y = 0; y<projectedArray[0].length; y++){
                for(int face = 0; face<6; face++){
                    gridDrawingManager.drawTriangle(x+15, y-14, projectedArray[x][y][face], face);
                }
            }
        }
    }

    //paints the gridManager to the screen.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(gridDrawingManager,0,0,null);
    }
}