package Testing.Tests;

import World.DataStorage.Octree.ActiveBranch;
import World.DataStorage.Octree.Octree;

import static java.lang.Long.toBinaryString;

public class ActiveBranchTesting {

    //runs all testing methods
    public void run(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~ ActiveBranchTesting ~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");

        //run tests
        testRelativeKeys();

        System.out.println("~~~~~~~~~~~~~~~~~~~~");
    }

    private void testRelativeKeys() {
        ActiveBranch activeBranch = new ActiveBranch();

        long key = 1 << 6;
        key |= 1 << 7;

        printUnpackedKeyBinary(key);

        key = activeBranch.subtractToBit(key, 0, 1);

        printUnpackedKeyBinary(key);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  De bugging
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Methods for debugging
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
