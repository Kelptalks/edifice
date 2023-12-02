package World;

import GameData.GameData;
import World.ActiveBranch.ActiveArea;
import World.ActiveBranch.ActiveBranch;
import World.Octree.Branch;
import World.Octree.Octree;
import World.TerrainGen.TerrainGen;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  World
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Class to manage the game world
 *  and generate terrain
 */
public class World{
    private ActiveArea activeArea;
    private Octree octree;
    private TerrainGen terrainGen;

    public World(GameData gameData){
        //set up Octree
        int depth = 20;
        this.octree = new Octree(gameData, depth);

        //set up terrain generation
        long groundHeight = octree.getDimension(depth);
        System.out.println("Ground height : " + groundHeight);
        this.terrainGen = new TerrainGen(groundHeight);
    }

    public Branch loadBranch(long key, int depth){
        Branch branch = octree.loadBranch(key, depth);
        octree.populate(branch);
        terrainGen.generateBranch(branch, key, depth);

        return branch;
    }

    public ActiveArea getActiveArea(){
        return this.activeArea;
    }
}
