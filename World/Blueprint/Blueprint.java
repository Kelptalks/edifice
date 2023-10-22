package World.Blueprint;
/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  blueprint
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * A mini world-class that can be stored and pasted
 * into the octree
 */
public class Blueprint {

    private int[][][] bluePrint;
    private int[] origin;
    public Blueprint(int xSize, int ySize, int zSize){
        bluePrint = new int[xSize][ySize][zSize];
        centerOrigin(xSize, ySize, zSize);
    }

    //Creates an origin center so negative cords can be allowed.
    public void centerOrigin(int xSize, int ySize, int zSize){
        origin = new int[3];
        origin[0] = xSize / 2;
        origin[1] = ySize / 2;
        origin[2] = zSize / 2;
    }

    //Get the block
    public int getBlock(int xCor, int yCor, int zCor){
        if (checkIfInBounds(xCor, yCor, zCor)) {
            return bluePrint[xCor + origin[0]][yCor + origin[1]][zCor + origin[2]];
        }
        return 0;
    }

    //set a block
    public void setBlock(int xCor, int yCor, int zCor, int blockType){
       if (checkIfInBounds(xCor, yCor, zCor)){
           bluePrint[xCor + origin[0]][yCor + origin[1]][zCor + origin[2]] = blockType;
       }
    }

    //Check cords
    public boolean checkIfInBounds(int xCor, int yCor, int zCor){
        if ((xCor >= - origin[0]) && xCor < (bluePrint.length - origin[0])){
            if ((yCor >= - origin[1]) && yCor < (bluePrint[0].length - origin[1])){
                if ((yCor >= - origin[2]) && yCor < (bluePrint[0][0].length - origin[2])){
                    return true;
                }
            }
        }
        return false;
    }
}
