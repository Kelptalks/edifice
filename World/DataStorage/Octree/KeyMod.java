package World.DataStorage.Octree;

import static java.lang.Long.toBinaryString;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  keyMod
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * tools for manipulating keys and debugging them.
 */
public class KeyMod {
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  axis-based key bit manipulation
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Manipulate key based on a cord system
     *
     * bitmask(0) = x axis
     * bitmask(1) = y axis
     * bitmask(2) = z axis
     *
     * Calls recursively to manipulate higher
     * level axis.
     */

    //adding/subtracting from all axis's
    public long getRelativeKey(long key, int depth, long xMod, long yMod, long zMod){

        key = modAxis(key, depth, 0, xMod);
        key = modAxis(key, depth, 1, yMod);
        key = modAxis(key, depth, 2, zMod);

        return key;
    }

    //adding or subtracting multiple bits from an axis
    public long modAxis(long key, int depth, int mask, long mod){
        if (mod > 0){
            //Adds bits to x-axis
            for (int x = 0; x < mod; x++) {
                key = addToBit(key, depth, mask);
            }
        }
        else if (mod < 0){
            //Subtracts bits to x-axis
            for (int x = 0; x > mod; x--) {
                key = subtractToBit(key, depth, mask);
            }
        }
        return key;
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Custom arithmetical key axis manipulation
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * tools for adding and subtracting to bits
     * based off there axis.
     */

    //adding a single bit to an axis
    public long addToBit(long key, int depth, int bitMask){
        long mask = 1L << ((depth * 3) + bitMask);

        //if the z bit is != 0, then the sector on a higher branch level needs to be set.
        if ((key & mask) != 0){
            key &= ~mask;
            // Recursively add to the bit at the next higher level.
            key = addToBit(key, depth + 1, bitMask);
        }

        //else, the key can be increased and remain in the same sector
        else {
            key |= mask;
        }
        return key;
    }

    //subtracting a single bit to an axis
    public long subtractToBit(long key, int depth, int bitMask){
        long mask = 1L << ((depth * 3) + bitMask);

        // if the bit is == 0, then the sector on a lower branch level needs to be set.
        if ((key & mask) == 0){
            key |= mask;
            // Recursively subtract to the bit at the next higher level.
            key = subtractToBit(key, depth + 1, bitMask);
        }

        // else the key can be subtracted and remain in the same sector;
        else {
            key &= ~mask;
        }
        return key;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Axis based cord conversion
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Converting a key into coordinates.
     */

    //convert a cord to an axis
    public long[] keyToCords(long key){

        int maxDepth = 21;
        long[] cords = new long[3];

        //cycle through the key
        for (int axis = 0; axis < 3; axis++){
            cords[axis] = axisToCord(key, axis);
        }
        return cords;
    }

    //convert an axis to a cord
    public long axisToCord(long key, int axis){
        long cord = 0;
        int maxDepth = 21;
        for (int depth = 0; depth < maxDepth; depth++){
            long mask = 1L << ((depth * 3) + axis);
            if ((key & mask) != 0){
                cord |= 1L << depth;
            }
        }
        return cord;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Debugging
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * tools for displaying and debugging keys.
     */

    public int[] unpackKey(long key){
        int keySize = 22;
        int[] unpackedKey = new int[keySize];

        //cycle through depths, stop when the desired depth is reached
        for (int maxKeySize = keySize-1; maxKeySize >= 0; maxKeySize--) {
            //get 3 bits of the key based on depth
            unpackedKey[maxKeySize] = (int) ((key >> (3 * maxKeySize)) & 0x07);
        }
        return unpackedKey;
    }

    public void printUnpackedKey(long key){
        int[] unpackedKey = unpackKey(key);
        System.out.print("Key = ");
        for (int index : unpackedKey){
            System.out.print(index + ", ");
        }
        System.out.println(" ");
    }

    public void printUnpackedKeyBinary(long key){
        int[] unpackedKey = unpackKey(key);
        System.out.print("Key = ");
        for (int index : unpackedKey){
            System.out.print(toBinaryString(index) + ", ");
        }
        System.out.println(" ");
    }
}
