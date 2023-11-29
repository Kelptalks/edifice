import GUI.GUI;
import GameData.GameData;
import MainLoop.MainLoop;

public class App {
    public static void main(String[] args) throws Exception {
        GameData gameData = new GameData();
        GUI gameFrame = new GUI(gameData);
    }
}
