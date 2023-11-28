package Testing.Tests.Octree;

import GameData.GameData;
import World.Octree.Leaf;

public class LeafTesting {

    //runs all testing methods
    public void run(){
        System.out.println("~~~~~~~~~~~~~~~~~");
        System.out.println("~~ LeafTesting ~~");
        System.out.println("~~~~~~~~~~~~~~~~~");

        //run tests
        testSetAndGetBlock();

        System.out.println("~~~~~~~~~~~~~~~~~");
    }

    public void testSetAndGetBlock(){
        Leaf leaf = new Leaf();
        for (int x = 0; x < 4095; x++){
            leaf.setBlock(x, 3);
        }

        int totalNot = 0;
        leaf.setBlock(251, 8);
        leaf.setBlock(672, 8);
        leaf.setBlock(123, 8);
        leaf.setBlock(23, 8);
        leaf.setBlock(2993, 8);

        for (int x = 0; x < 4095; x++){
            if (leaf.getBlock(x) != 3){
                totalNot++;
            }
        }

        if (totalNot == 5){
            System.out.println("-(pass)");
        }
        else {
            System.out.println("-(fail)");
        }
    }
}
