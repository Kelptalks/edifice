package GUI.Menus.Renderer.Controls;

import GUI.Menus.Renderer.Renderer;
import GameData.GameData;
import World.DataStorage.Blueprint.Blueprint;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class MouseInputs implements MouseListener {
    private GameData gameData;
    private Renderer renderer;
    public MouseInputs(GameData gameData){
        this.gameData = gameData;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        //WAY TO IMPROVE!!
        //get mouse inputs and set them to a current menu
        //this is really poorly done, and can be fixed
        //I can move a ray tracing algorithm directly in
        //here and modify a shared world in the game data.

        //get mouse inputs and set it to renderer to modifier a block
        this.renderer = (Renderer) gameData.menu;
    }

    private long[] pathLeftTopCoords(long x, long y){
        Blueprint world = new Blueprint(1000, 1000, 1000);

        int z = 0;
        int block = world.getBlock(x, y, z);
        for (int distance = 0; distance < 500; distance++)
        {
            x--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                return new long[]{x, y, z};
            }

            y--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                return new long[]{x, y, z};
            }

            z--;
            block = world.getBlock(x, y, z);
            if (block != 0){
                return new long[]{x, y, z};
            }
        }
        return new long[]{x, y, z};
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
