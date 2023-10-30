package World.DataStorage.Blueprint;
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



        for (int x = -5; x < 5; x++){
            for (int y = -5; y < 5; y++){
                setBlock(x, y, -13, 3);
            }
        }

        for (int x = -5; x < 5; x++){
            for (int y = -5; y < 5; y++){
                setBlock(x, y, -13, 3);
            }
        }

        for (int x = -3; x < 3; x++){
            for (int y = -3; y < 3; y++){
                for (int z = -13; z < -5; z++) {
                    setBlock(x, y, z, 2);
                }
            }
        }

        for (int x = -5; x < 5; x++){
            for (int y = -5; y < 5; y++){
                setBlock(x, y, -13, 3);
            }
        }


    }

    //Creates an origin center so negative cords can be allowed.
    public void centerOrigin(int xSize, int ySize, int zSize){
        origin = new int[3];
        origin[0] = xSize / 2;
        origin[1] = ySize / 2;
        origin[2] = zSize / 2;
    }

    //Get the block
    public int getBlock(long xCor, long yCor, long zCor){
        if (checkIfInBounds(xCor, yCor, zCor)){
            return bluePrint[(int) (xCor + origin[0])][(int) (yCor + origin[1])][(int) (zCor + origin[2])];
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
    public boolean checkIfInBounds(long xCor, long yCor, long zCor){
        //System.out.print(xCor + " | " + yCor + " | " + zCor);
        if ((xCor + origin[0] >= 0) && xCor < (bluePrint.length - origin[0])){
            if ((yCor + origin[1] >= 0) && yCor < (bluePrint[0].length - origin[1])){
                if ((zCor + origin[2] >= 0) && zCor < (bluePrint[0][0].length - origin[2])){
                    //System.out.println("true");
                    return true;
                }
            }
        }
        //System.out.println("false");
        return false;
    }
}
