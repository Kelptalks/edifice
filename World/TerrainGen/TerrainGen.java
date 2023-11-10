package World.TerrainGen;

import World.DataStorage.Octree.Branch;
import World.DataStorage.Octree.KeyMod;
import World.DataStorage.Octree.Leaf;
import World.DataStorage.Octree.Octree;

import java.awt.*;
import java.util.Random;

public class TerrainGen{
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  TerrainGen
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Generate terrain
     */

    private long groundHeight;

    public TerrainGen(long groundHeight){
        this.groundHeight = groundHeight;
    }
    KeyMod keyMod = new KeyMod();

    public void generateBranch(Branch branch, long key, int depth){
        branch.getBranch(4).fillLeaves();
        branch.getBranch(5).fillLeaves();
        branch.getBranch(6).fillLeaves();
        branch.getBranch(7).fillLeaves();

        genTree(branch, 0, 0);
        genTree(branch, 8, 16);
        genTree(branch, 18, 12);
        genTree(branch, -5, 10);

        System.out.println(branch.getLeafCount());


        //branch.setBlock(root, 7);


    }

    public void genTree(Branch branch, int x, int y){
        long root =(long) Math.pow(8, 2L << branch.getDepth());
        root = keyMod.getRelativeKey(root, 0, 10 + x, +10 + y, 1);
        Random random = new Random();

        int height = random.nextInt(5, 15);


        for (int z = 0; z < height; z++){
            branch.setBlock(keyMod.getRelativeKey(root, 0, 0, 0, z), 5);

            if (height > 8 && z == 5){
                int xMod = 0;
                int yMod = 1;
                if (random.nextBoolean()){
                    xMod = 1;
                    yMod = 0;
                }
                branch.setBlock(keyMod.getRelativeKey(root, 0, yMod, xMod, z), 5);
                branch.setBlock(keyMod.getRelativeKey(root, 0, yMod * 2, xMod * 2, z), 5);
                branch.setBlock(keyMod.getRelativeKey(root, 0, yMod * 2, xMod * 2, z + 1), 5);
                genTreeTop(branch, keyMod.getRelativeKey(root, 0, yMod * 2, xMod * 2, z + 1));
            }

        }

        root = keyMod.modAxis(root, 0, 2, height);

        genTreeTop(branch, root);

    }

    public void genTreeTop(Branch branch, long key){
        branch.setBlock(keyMod.modAxis(key, 0, 0, -1), 7);
        branch.setBlock(keyMod.modAxis(key, 0, 0, 1), 7);

        branch.setBlock(keyMod.modAxis(key, 0, 1, -1), 7);
        branch.setBlock(keyMod.modAxis(key, 0, 1, 1), 7);

        branch.setBlock(keyMod.modAxis(key, 0, 2, 1), 7);
    }

}
