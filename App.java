import Testing.Testing;
import World.Blueprint.Blueprint;
import World.World;

public class App {
    public static void main(String[] args) throws Exception {
        //Testing testing = new Testing();
        GameFrame gameFrame = new GameFrame();

        while (true) {
            Thread.sleep(20);
            gameFrame.renderView();
        }
    }
}
