import Testing.Testing;
import World.Blueprint.Blueprint;
import World.World;

public class App {
    public static void main(String[] args) throws Exception {
        //Testing testing = new Testing();

        Blueprint blueprint = new Blueprint(100, 100, 100);
        blueprint.setBlock(50, 49, 49, 5);
        System.out.println(blueprint.getBlock(50, 49, 49));

        //GameFrame gameFrame = new GameFrame();
    }
}
