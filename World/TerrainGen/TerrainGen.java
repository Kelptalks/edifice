package World.TerrainGen;

import World.Octree.Branch;
import World.Octree.KeyMod;
import World.Octree.Leaf;
import World.Octree.Octree;

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
        branch.getBranch(4).fillLeaves(2);
        branch.getBranch(5).fillLeaves(2);
        branch.getBranch(6).fillLeaves(2);
        branch.getBranch(7).fillLeaves(2);

        Random random = new Random();
        for (int x = -200; x < 200; x++){
            for (int y = -200; y < 200; y++){
                if (random.nextInt(0, 200) == 0){
                    genTree(branch, x, y);
                }
            }
        }

        long root = (long) Math.pow(8, 2L << branch.getDepth());
        for (int x = -200; x < 200; x++){
            for (int y = -200; y < 200; y++){
                branch.setBlock(keyMod.getRelativeKey(root, 0, x, y, 0), 4);
            }
        }
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Shape generation
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * generating basic shapes
     */

    public void genCuboid(long x, long y, long z, long width, long height){

    }

    public void genSphere(long x, long y, long z, long width, long height){

    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Generate a tree
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * generate a tree
     */

    public void genTree(Branch branch, int x, int y){
        long root = (long) Math.pow(8, 2L << branch.getDepth());
        root = keyMod.getRelativeKey(root, 0, 10 + x, +10 + y, 1);
        Random random = new Random();

        int height = random.nextInt(5, 15);


        for (int z = 0; z < height; z++){
            branch.setBlock(keyMod.getRelativeKey(root, 0, 0, 0, z), 5);
            //branch generation
            if (height > 8 && z == 5){
                int xMod = 0;
                int yMod = 1;
                if (random.nextBoolean()){
                    xMod = 1;
                    yMod = 0;
                }
                if (random.nextBoolean()){
                    xMod *= -1;
                    yMod *= -1;
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
