import GUI.GUI;
import GameData.GameData;
import MainLoop.Events.TestEvent;
import MainLoop.MainLoop;

public class App {
    public static void main(String[] args) throws Exception {
        GameData gameData = new GameData();
        gameData.setUpWorld();

        //start renderer
        GUI gameFrame = new GUI(gameData);

        //Start logic loop
        Thread gameLoop = new Thread(new MainLoop(gameData));
        gameLoop.start();
    }
}
