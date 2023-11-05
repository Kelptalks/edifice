package Testing.Tests.Octree;

import World.DataStorage.Octree.KeyMod;


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
        testBitSubtract();
        testBitAdd();
        testGetRelativeKey();

        System.out.println("~~~~~~~~~~~~~~~~");
    }


    public void testGetRelativeKey(){
        KeyMod keyMod = new KeyMod();

        long key = 45;

        keyMod.printUnpackedKeyBinary(key);

        key = keyMod.getRelativeKey(45, 0,  -1, 2, 0);

        keyMod.printUnpackedKeyBinary(key);
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
