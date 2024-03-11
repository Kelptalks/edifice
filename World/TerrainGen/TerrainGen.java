package World.TerrainGen;

import World.Octree.Branch;
import World.Octree.KeyMod;
import GameData.Block;

import java.awt.*;
import java.util.Random;

public class TerrainGen{
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  TerrainGen
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Generate terrain
     *
     * Need to add a system for generating
     * a library of shapes
     * need a system for adding those shapes
     * to the world based on factors like
     * spacing, scale, location
     *
     * I will also need to implement a method
     * for large-scale generated features
     * to be planed but not generated in unexplored
     * chunks.
     *
     */

    private long groundHeight;

    public TerrainGen(long groundHeight){
        this.groundHeight = groundHeight;
    }
    KeyMod keyMod = new KeyMod();

    public void generateBranch(Branch branch, long key, int depth){
        branch.getBranch(4).fillLeaves(Block.Stone.id);
        branch.getBranch(5).fillLeaves(Block.Stone.id);
        branch.getBranch(6).fillLeaves(Block.Stone.id);
        branch.getBranch(7).fillLeaves(Block.Stone.id);

        Random random = new Random();
        for (int x = -200; x < 200; x++){
            for (int y = -200; y < 200; y++){
                if (random.nextInt(0, 1000) == 0){
                    //genTree(branch, x, y);
                    genFungi(branch, x, y);
                }
            }
        }

        long root = (long) Math.pow(8, 2L << branch.getDepth());
        for (int x = -400; x < 400; x++){
            for (int y = -400; y < 400; y++){
                branch.setBlock(keyMod.getRelativeKey(root, 0, x, y, 0), Block.BlueGrass);

            }
        }

        genHive(branch, root);

        //branch.setBlock(onScreen, Block.Fruit);
    }

    public void genHive(Branch branch, long root){
        //hive gen testing
        long onScreen = keyMod.getRelativeKey(root, 0, 220, 150, -35);
        genSphere(branch, onScreen, 35, Block.Stone);

        onScreen = keyMod.getRelativeKey(onScreen, 0, 0, 0, 35);
        long onScreen2 = onScreen;
        for (int x = 0; x < 5; x++){
            onScreen = keyMod.getRelativeKey(onScreen, 0, 0, 2, 2);
            onScreen2 = keyMod.getRelativeKey(onScreen2, 0, 1, 0, 3);
            genSphere(branch, onScreen, 8, Block.Fruit);
            genSphere(branch, onScreen2, 8, Block.Fruit);
        }

        onScreen = keyMod.getRelativeKey(root, 0, 220, 150, 0);
        for(int x = 0; x< 10; x++){
            onScreen = keyMod.getRelativeKey(onScreen, 0, 0, 2, 2);
            onScreen2 = keyMod.getRelativeKey(onScreen2, 0, 1, 0, 3);
            genSphere(branch, onScreen, 6, Block.Air);
            genSphere(branch, onScreen2, 6, Block.Air);
        }
    }

    public void genLine(long start, long end, int width, Block block){

    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Shape generation
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * generating basic shapes
     */

    public void genCuboid(Branch branch, long key, long xScale, long yScale, long zScale){
        for (int z = 0; z < zScale; z++){
            for (int y = 0; y < yScale; y++){
                for (int x = 0; x < xScale; x++){
                    branch.setBlock(keyMod.getRelativeKey(key, 0, x, y, z), Block.Air);
                }
            }
        }
    }

    public void genCircle(Branch branch, long key, long radius, Block block){
        float angleInterval = 0.1F;
        for (float angle = 0; angle < 360; angle += angleInterval){
            for (int currentRadius = 0; currentRadius < radius; currentRadius++){
                int xCor = (int) (Math.cos(Math.toRadians(angle)) * currentRadius);
                int yCor = (int) (Math.sin(Math.toRadians(angle)) * currentRadius);
                branch.setBlock(keyMod.getRelativeKey(key, 0, xCor, yCor, 0), block);
            }
        }
    }

    public void genSphere(Branch branch, long key, long radius, Block block){
        float angleInterval = 90F /radius;
        for (float angle = 0; angle < 180; angle += angleInterval){
            key = keyMod.getRelativeKey(key, 0, 0, 0, 1);
            int zRadius = (int) (Math.sin(Math.toRadians(angle)) * radius);
            genCircle(branch, key, zRadius, block);
        }
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
            branch.setBlock(keyMod.getRelativeKey(root, 0, 0, 0, z), Block.Log.id);
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
                branch.setBlock(keyMod.getRelativeKey(root, 0, yMod, xMod, z), Block.Log.id);
                branch.setBlock(keyMod.getRelativeKey(root, 0, yMod * 2, xMod * 2, z), Block.Log.id);
                branch.setBlock(keyMod.getRelativeKey(root, 0, yMod * 2, xMod * 2, z + 1), Block.Log.id);
                genTreeTop(branch, keyMod.getRelativeKey(root, 0, yMod * 2, xMod * 2, z + 1));
            }
        }

        root = keyMod.modAxis(root, 0, 2, height);

        genTreeTop(branch, root);

    }

    public void genFungi(Branch branch, int x, int y) {
        long root = (long) Math.pow(8, 2L << branch.getDepth());
        root = keyMod.getRelativeKey(root, 0, 10 + x, +10 + y, 1);
        Random random = new Random();

        int height = random.nextInt(20, 100);
        for (int z = 0; z < height; z++){
            genCircle(branch, keyMod.getRelativeKey(root, 0, x, y, z), (Math.abs(z-height))/10 , Block.Fungi);
        }

    }

    public void genTreeTop(Branch branch, long key){
        branch.setBlock(keyMod.modAxis(key, 0, 0, -1), Block.Leaf);
        branch.setBlock(keyMod.modAxis(key, 0, 0, 1), Block.Leaf);
        branch.setBlock(keyMod.modAxis(key, 0, 1, -1), Block.Leaf);
        branch.setBlock(keyMod.modAxis(key, 0, 1, 1), Block.Leaf);
        branch.setBlock(keyMod.modAxis(key, 0, 2, 1), Block.Leaf);
    }

}
