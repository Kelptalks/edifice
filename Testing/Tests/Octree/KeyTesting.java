package Testing.Tests.Octree;

import World.Octree.KeyMod;


/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Key testing
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Key Info:
 * Keys are organized into 3-
 * bit chunks. Each of the bits
 * in this 3-bit value are each
 * axis. X is 0, y is 1, and
 * z is 2.
 */
public class KeyTesting {

    public void run(){
        System.out.println("~~~~~~~~~~~~~~~~");
        System.out.println("~~ KeyTesting ~~");
        System.out.println("~~~~~~~~~~~~~~~~");

        //run tests
        keyToCords();
        testBitSubtract();
        testBitAdd();
        testGetRelativeKey();

        System.out.println("~~~~~~~~~~~~~~~~");
    }


    public void testGetRelativeKey(){
        KeyMod keyMod = new KeyMod();

        long key = 45;

        key = keyMod.getRelativeKey(45, 0,  -1, 2, 0);
    }

    public void keyToCords(){
        KeyMod keyMod = new KeyMod();

        long key = 500000;
        int x = -1;
        int y = -50;
        int z = 0;

        //set the axis
        key = keyMod.getRelativeKey(0, 0,  x, y, z);

        long[] cords = keyMod.keyToCords(key);

        //check the returned value
        if (cords[0] != keyMod.axisToCord(key, 0)){
            System.out.println("-(fail) key to cords x axis expected(" + keyMod.axisToCord(key, 0) + "), but was (" + cords[0] + ")");
        }
        else if (cords[1] != keyMod.axisToCord(key, 1)){
            System.out.println("-(fail) key to cords y axis expected(" + keyMod.axisToCord(key, 1) + "), but was (" + cords[1] + ")");
        }
        else if (cords[2] != keyMod.axisToCord(key, 2)){
            System.out.println("-(fail) key to cords z axis expected(" + keyMod.axisToCord(key, 2) + "), but was (" + cords[2] + ")");
        }
        else{
            System.out.println("-(pass) key to cords");
        }

    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Relative key manipulation
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Test that the propper bits
     * of the key are manipulated
     * when adding and subtracting
     * from specific axes.
     *
     * The method should be able
     * to manipulate keys on
     * each axis and have values
     * carry over to the neighboring
     * 3-bit value if that is necessary.
     */
    private void testBitAdd(){
        KeyMod keyMod = new KeyMod();

        long keyLong = 1 << 6;

        keyLong |= 1 << 7;
        keyLong = keyMod.addToBit(keyLong, 2, 1);

        //get unpacked key for comparison.
        int[] unpackedKey = keyMod.unpackKey(keyLong);

        //test if the key was manipulated properly.
        if ((unpackedKey[2] != 1) || (unpackedKey[3] != 2)){
            System.out.println("-(fail) Key was not manipulated successfully");
        }
        else{
            System.out.println("-(pass) testBitAdd");
        }
    }

    private void testBitSubtract(){
        KeyMod keyMod = new KeyMod();

        long keyLong = 1 << 6;

        keyLong |= 1 << 7;
        keyLong = keyMod.subtractToBit(keyLong, 1, 1);

        //get unpacked key for comparison.
        int[] unpackedKey = keyMod.unpackKey(keyLong);

        //test if the key was manipulated properly.
        if ((unpackedKey[2] != 1) || (unpackedKey[1] != 2)){
            System.out.println("-(fail) Key was not manipulated successfully");
        }
        else{
            System.out.println("-(pass) testBitSubtract");
        }
    }
}
