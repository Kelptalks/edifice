package Testing.Tests;

import World.DataStorage.Octree.ActiveBranch;

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

        //This is the number of blocks per sector at a depth of 7
        long cubeSize = 2097152;

        //If I multiply the cube size by the sector, 0-7 I get the key for that sector
        int sector = 3;

        activeBranch.posYKey(cubeSize * 7);

    }
}
