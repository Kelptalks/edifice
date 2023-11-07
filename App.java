import GUI.GUI;
import GameData.gameData;
import Testing.Testing;

public class App {
    public static void main(String[] args) throws Exception {
        gameData gameData = new gameData();
        GUI gameFrame = new GUI(gameData);


        Testing testing = new Testing();
        testing.testActiveBranch();


    }
}
