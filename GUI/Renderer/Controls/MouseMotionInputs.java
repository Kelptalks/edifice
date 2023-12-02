package GUI.Renderer.Controls;

import GUI.Renderer.Effects.Effects;
import GameData.GameData;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionInputs implements MouseMotionListener {
    private GameData gameData;
    private Effects effects;
    public MouseMotionInputs(GameData gameData, Effects effects){
        this.gameData = gameData;
        this.effects = effects;
    }
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        effects.drawPoint(e.getX(), e.getY());
    }
}
