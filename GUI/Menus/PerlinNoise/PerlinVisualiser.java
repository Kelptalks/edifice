package GUI.Menus.PerlinNoise;
import GameData.gameData;
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
    private Graphics2D graphics = (Graphics2D) this.getGraphics();
    private PerlinNoiseGen perlinNoiseGen = new PerlinNoiseGen();
    public PerlinVisualiser(gameData gameData) {
        super(gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ, TYPE_4BYTE_ABGR_PRE);

        //drawVector();
        drawGrid(50);
        perlinNoiseGen.generateVector();
    }

    //drawing arrows based off vector cords
    public void drawGrid(int scale){
        for (int y = 0; y < 50; y++){
            for (int x = 0; x < 50; x++){
                int pointX = x * scale;
                int pointY = y * scale;

                graphics.setColor(Color.BLACK);
                graphics.fillOval(pointX- 8, pointY- 8, 16, 16);

                drawVector(pointX, pointY, scale);
            }
        }
    }

    public void drawVector(int x, int y, int scale){


        double[] vector = perlinNoiseGen.generateVector();
        graphics.setColor(Color.RED);

        graphics.setStroke(new BasicStroke(2));
        graphics.drawLine(x, y, (int) (x + (vector[0] * scale)), (int) (y + (vector[1] * scale)));
    }
}
