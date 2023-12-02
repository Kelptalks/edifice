package MainLoop;

import GameData.GameData;
import MainLoop.Events.TestEvent;
import MainLoop.TikManager.TikManager;

public class MainLoop implements Runnable{
    private GameData gameData;

    public MainLoop(GameData gameData){
        this.gameData = gameData;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            gameData.tikManager.executeNextTik();
        }
    }
}
