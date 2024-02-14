package GUI.IsoRenderer.Controls;

import GUI.IsoRenderer.Camera.CameraData;
import GameData.GameData;

import javax.swing.plaf.synth.SynthUI;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PlayerMouseInputs implements MouseMotionListener, MouseListener {

    private CameraData cameraData;
    private GameData gameData;
    public PlayerMouseInputs(GameData gameData, CameraData cameraData){
        super();
        this.cameraData = cameraData;
        this.gameData = gameData;

    }


    int xStart = 0;
    int xEnd = 0;
    int xChange = 0;

    int yStart = 0;
    int yEnd = 0;
    int yChange = 0;

    @Override
    public void mouseDragged(MouseEvent e) {
        xEnd = e.getX();
        xChange = xStart - xEnd;
        cameraData.xCamOffSet -= xChange;
        xStart = xEnd;

        yEnd = e.getY();
        yChange = yStart - yEnd;
        cameraData.yCamOffSet -= yChange;
        yStart = yEnd;

    }


    @Override
    public void mouseMoved(MouseEvent e) {
        xEnd = e.getX();
        xChange = xStart - xEnd;
        xStart = xEnd;

        yEnd = e.getY();
        yChange -= yStart - yEnd;
        yStart = yEnd;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (cameraData.xCamOffSet > cameraData.xChunkPixelRez){
            //cameraData.xCamOffSet = cameraData.xCamOffSet % cameraData.xChunkPixelRez;
        }
        if (cameraData.xCamOffSet < -cameraData.xChunkPixelRez){
            //cameraData.xCamOffSet = cameraData.xCamOffSet % -cameraData.xChunkPixelRez;
        }

        if (cameraData.yCamOffSet > cameraData.yChunkPixelRez){
            //cameraData.yCamOffSet = cameraData.yCamOffSet % cameraData.yChunkPixelRez;
        }
        if (cameraData.yCamOffSet < -cameraData.yChunkPixelRez){
            //cameraData.yCamOffSet = cameraData.yCamOffSet % -cameraData.yChunkPixelRez;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
