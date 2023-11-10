package World;

import World.ActiveBranch.ActiveBranch;
import World.DataStorage.Octree.Branch;
import World.DataStorage.Octree.Octree;
import World.TerrainGen.TerrainGen;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  World
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Class to manage the game world
 *  and generate terrain
 */
public class World{

    private ActiveBranch activeBranch;
    private Octree octree;
    private TerrainGen terrainGen;

    public World(){
        //set up Octree
        int depth = 20;
        this.octree = new Octree(depth);

        //set up terrain generation
        long groundHeight = octree.getDimension(depth);
        System.out.println("Ground height : " + groundHeight);
        this.terrainGen = new TerrainGen(groundHeight);

        //set up active branch
        this.activeBranch = new ActiveBranch(this);
    }

    public Branch loadBranch(long key, int depth){
        Branch branch = octree.loadBranch(key, depth);
        octree.populate(branch);
        terrainGen.generateBranch(branch, key, depth);

        return branch;
    }
}
