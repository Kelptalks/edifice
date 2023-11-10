import GUI.GUI;
import GameData.GameData;
import Testing.Testing;

public class App {
    public static void main(String[] args) throws Exception {
        GameData gameData = new GameData();
        GUI gameFrame = new GUI(gameData);


        Testing testing = new Testing();
        testing.testKey();
        //testing.testActiveBranch();
    }
}
