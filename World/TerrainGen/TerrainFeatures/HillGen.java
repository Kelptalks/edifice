package World.TerrainGen.TerrainFeatures;

import GameData.Block;
import World.Octree.Branch;
import World.Octree.KeyMod;

import java.security.Key;

public class HillGen {

    private KeyMod keyMod = new KeyMod();
    public void buildHill(long key, Branch branch){
        genCuboid(branch, key, 50, 50, 50);
    }

    public void genCuboid(Branch branch, long key, long xScale, long yScale, long zScale){
        for (int z = 0; z < zScale; z++){
            for (int y = 0; y < yScale; y++){
                for (int x = 0; x < xScale; x++){
                    branch.setBlock(keyMod.getRelativeKey(key, 0, x, y, z), Block.Air);
                }
            }
        }
    }
}
