package GUI.IsoRenderer.Controls;

import GUI.IsoRenderer.Camera.CameraData;
import GUI.IsoRenderer.Camera.GridManager.Structure.CastedBlock;
import GUI.IsoRenderer.Camera.GridManager.Structure.CastedChunk;
import GUI.IsoRenderer.Textures.Texture;
import GameData.GameData;
import GameData.Block;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * PlayerMouseInputs
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
public class PlayerMouseInputs implements MouseMotionListener, MouseListener {

    private CameraData cameraData;
    private GameData gameData;
    public PlayerMouseInputs(GameData gameData, CameraData cameraData){
        super();
        this.cameraData = cameraData;
        this.gameData = gameData;

    }
    @Override
    public void mouseMoved(MouseEvent e) {
        trackMouseRelativeMovement(e);
    }

    private int buttonPressed = 0;
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Set button pressed
        buttonPressed = e.getButton();

        //handle left click functions
        if (buttonPressed == MouseEvent.BUTTON1){
            removeBlock(e);
        }
        //Handle right click functions
        if (buttonPressed == MouseEvent.BUTTON3){
            placeBlock(e);
        }
    }


    CastedBlock startDrag = null;
    @Override
    public void mouseDragged(MouseEvent e) {
        //If right click
        if (buttonPressed == MouseEvent.BUTTON2) {
            calculateTotalDragCamOffset(e);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //Reset button pressed
        buttonPressed = 0;
        //checkOffsetForChunkLoading();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Block manipulation
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */

    public void removeBlock(MouseEvent e){

        long key = cameraData.castedChunkManager.screenCordsToBlockKey(e.getX(), e.getY());

        System.out.println( "x : "  + gameData.keyMod.keyToCords(key)[0]);
        System.out.println("y : " + gameData.keyMod.keyToCords(key)[1]);
        System.out.println("z : " +gameData.keyMod.keyToCords(key)[2]);



        gameData.activeArea.setBlock(key, Block.Air.id);
        cameraData.castedChunkManager.renderChunkArea(e.getX(), e.getY());

    }

    public void placeBlock(MouseEvent e){
        cameraData.castedChunkManager.placeBlock(e.getX(), e.getY(), gameData.currentBlock);
        cameraData.castedChunkManager.renderChunkArea(e.getX(), e.getY());
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Camera offset management
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Functions for managing the offset
     * of the camera relative to a starting
     * position.
     * This includes identifying what direction new
     * chunks need to be loaded and old chunks
     * unloaded.
     */


    //check if a camera center has entered new chunk
    public void checkOffsetForChunkLoading(){
        if (cameraData.xCamOffSet > cameraData.xChunkPixelRez){
            cameraData.xCamOffSet = cameraData.xCamOffSet % cameraData.xChunkPixelRez;
        }
        if (cameraData.xCamOffSet < -cameraData.xChunkPixelRez){
            cameraData.xCamOffSet = cameraData.xCamOffSet % -cameraData.xChunkPixelRez;
        }
        if (cameraData.yCamOffSet > cameraData.yChunkPixelRez){
            cameraData.yCamOffSet = cameraData.yCamOffSet % cameraData.yChunkPixelRez;
        }
        if (cameraData.yCamOffSet < -cameraData.yChunkPixelRez){
            cameraData.yCamOffSet = cameraData.yCamOffSet % -cameraData.yChunkPixelRez;
        }
    }

    //variables for tracking mouse movements.
    int xStart = 0;
    int xEnd = 0;
    int xChange = 0;
    int yStart = 0;
    int yEnd = 0;
    int yChange = 0;

    //Track mouse movements real time relative to current offset
    public void trackMouseRelativeMovement(MouseEvent e){
        xEnd = e.getX();
        xChange = xStart - xEnd;
        xStart = xEnd;

        yEnd = e.getY();
        yChange -= yStart - yEnd;
        yStart = yEnd;
    }


    //Calculate total movement after drag event
    public void calculateTotalDragCamOffset(MouseEvent e){
        xEnd = e.getX();
        xChange = xStart - xEnd;
        cameraData.xCamOffSet -= xChange;
        xStart = xEnd;

        yEnd = e.getY();
        yChange = yStart - yEnd;
        cameraData.yCamOffSet -= yChange;
        yStart = yEnd;
    }
}
