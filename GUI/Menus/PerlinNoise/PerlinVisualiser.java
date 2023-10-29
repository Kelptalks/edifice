package GUI.Menus.PerlinNoise;
import GameData.GameData;
import World.TerrainGen.PerlinNoiseGen;

import java.awt.*;
import java.awt.image.BufferedImage;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  PerlinNoiseMenu
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Displays a visual representation
 *  of perlin generated perlin noise
 */
public class PerlinVisualiser extends BufferedImage {
    private Graphics graphics = this.getGraphics();
    private PerlinNoiseGen perlinNoiseGen = new PerlinNoiseGen();
    public PerlinVisualiser(GameData gameData) {
        super(gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ, TYPE_4BYTE_ABGR_PRE);

        drawGrid(perlinNoiseGen.getPoints());
    }

    //draw a grid based off a perlin scale
    public void drawGrid(int[] points){
        graphics.setColor(Color.BLACK);
        for (int x = 0; x < points.length; x++){
            graphics.fillOval(x*50, 500 + points[x], 10, 10);
        }

        graphics.setColor(Color.GREEN);
        int[] octivePoints = perlinNoiseGen.genOctave(4);
        for (int x = 0; x < octivePoints.length; x++){
            graphics.fillOval(x*50, 500 + octivePoints[x] , 10, 10);
        }
    }

    //drawing arrows based off vector cords
    public void drawVector(){
        graphics.setColor(Color.BLACK);

    }
}
