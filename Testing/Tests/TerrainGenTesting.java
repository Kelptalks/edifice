package Testing.Tests;

import World.DataStorage.Octree.KeyMod;
import World.DataStorage.Octree.Leaf;
import World.TerrainGen.TerrainGen;

public class TerrainGenTesting {
    public void run() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~ TerrainGenTesting ~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");

        testGroundHeight();

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void testGroundHeight(){
        TerrainGen terrainGen = new TerrainGen(2342);
        KeyMod keyMod = new KeyMod();

        long key = keyMod.getRelativeKey(0, 0, 50, 50, 50);

        //Leaf leaf = terrainGen.genLeaf(key);
        //System.out.println(leaf.getBlock(592));

    }
}
