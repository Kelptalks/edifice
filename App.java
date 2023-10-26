import GUI.GUI;
import GameData.GameData;

public class App {
    public static void main(String[] args) throws Exception {
        //Testing testing = new Testing();
        GameData gameData = new GameData();
        GUI gameFrame = new GUI(gameData);

    }
}
