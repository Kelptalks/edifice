package Testing.Tests.Octree;

import World.ActiveBranch.ActiveBranch;
import World.World;

public class ActiveBranchTesting {

    //runs all testing methods
    public void run(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~ ActiveBranchTesting ~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");

        testDirectionalLoading();

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void testDirectionalLoading(){
        ActiveBranch activeBranch = new ActiveBranch(new World());
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  De bugging
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Methods for debugging
     */
}
