package Testing.Tests.Octree;

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
        testLoadUp();
        testCorToKey();

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void testCorToKey(){
        ActiveBranch activeBranch = new ActiveBranch();

        activeBranch.setBlock(1, 1, 1, 5);
        activeBranch.setBlock(1, 1, -1, 5);

        System.out.println("testing");
        System.out.println(activeBranch.getBlock(1, 1, 1));

    }

    private void testLoadUp(){
        ActiveBranch activeBranch = new ActiveBranch();
        activeBranch.loadUp();
        System.out.println("-(pass) testLoadUp");

    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  De bugging
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Methods for debugging
     */
}
