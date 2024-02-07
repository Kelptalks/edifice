package GUI.IsoRenderer.GridManager;

import java.awt.*;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
public class GridManager {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Conversions
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Methods to convert to iso cords
     */

    int scale = 32;

    public int[] IsoToScreen(int x, int y, int scale){
        int xCor = (x * scale) + -(y * scale);
        int yCor = (x * (scale / 2)) + (y * (scale / 2));
        return new int[]{xCor, yCor};
    }
    public int[] IsoToScreen(int[] cords){
        int xCor = (cords[0] * scale) + -(cords[1] * scale);
        int yCor = (cords[0] * (scale / 2)) + (cords[1] * (scale / 2));
        return new int[]{xCor, yCor};
    }

    //Triangle Polygon Tool
    public Polygon getRightTriangle(int xCor, int yCor, int scale){
        Polygon polygon = new Polygon();
        polygon.npoints = 3;
        polygon.xpoints = new int[]{xCor, xCor + (2 * scale), xCor};
        polygon.ypoints = new int[]{yCor, yCor + (scale), yCor + (2 * scale)};
        return polygon;
    }

    public Polygon getLeftTriangle(int xCor, int yCor, int scale){
        Polygon polygon = new Polygon();
        polygon.npoints = 3;
        polygon.xpoints = new int[]{xCor, xCor + (2  * scale), xCor + (2 * scale)};
        polygon.ypoints = new int[]{yCor + (scale), yCor, yCor + (2 * scale)};
        return polygon;
    }

    public void ScreenToIso(){
        //Image newImage = yourImage.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
    }

    public long screenCorToKey(int x, int y){
        return 0;
    }

    public boolean isOnScreen(long key){
        return false;
    }
}
