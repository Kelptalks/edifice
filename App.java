import GUI.GUI;

public class App {
    public static void main(String[] args) throws Exception {
        //Testing testing = new Testing();
        GUI gameFrame = new GUI();

        while (true) {
            Thread.sleep(20);
            gameFrame.renderView();
        }
    }
}
