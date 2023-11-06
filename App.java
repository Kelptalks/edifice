import GUI.GUI;
import GUI.Menus.Renderer.raycastRendering.RaycastRenderer;
import GameData.GameData;
import Testing.Testing;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        //Testing testing = new Testing();
        GameData gameData = new GameData();
        GUI gameFrame = new GUI(gameData);


        Testing testing = new Testing();
        testing.testActiveBranch();


    }
}
