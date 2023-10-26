package Testing.PackageTests;
import Testing.TestingTemplate;
import World.DataStorage.Octree.Leaf;
import World.DataStorage.Octree.Octree;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 */
public class OctreTesting extends TestingTemplate {

    @Override
    public void run(){

        testOctree();
    }

    public void testOctree(){
        Octree octree = new Octree();
        //octree.getInfo();

        //test the input of keys being unpacked to octree cords
        //System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        //System.out.println("Key Test");
        //System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        int key = 2875;

        //extreme depths!
        octree.expand(1);
        octree.expand(1);
        octree.expand(1);
        octree.expand(1);
        octree.expand(1);
        octree.expand(1);
        octree.expand(1);
        octree.expand(1);
        octree.expand(1);
        octree.expand(1);
        octree.expand(1);
        octree.expand(1);
        octree.expand(1);


        int[] octreeCoords = octree.unpackOctreeKey(key);

        //lock Adding.
        long t1 = System.nanoTime();
        long blocks = 10000000L;;
        System.out.println("Blocks to set: " + blocks);
        for (int x = 0; x < blocks; x++){
            octree.setBlock(x, 12);
        }
        System.out.println("Time to complete : " + ((System.nanoTime() - t1)/(1000000)) + " milliseconds");

        System.out.println(octree.getBlock(99999998));


        octree.navTree();

    }

    public void testLeaf(){
        Leaf leaf = new Leaf();
        System.out.println("Created Leaf");
        System.out.println("Blocks in leaf : " + leaf.getLeaf().length);
        leaf.setBlock(8, 3 , 0, 3);
        System.out.println(leaf.getBlock(8+3*16));
    }


}
