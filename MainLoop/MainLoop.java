package MainLoop;

import GameData.GameData;
import MainLoop.TikManager.TikManager;

public class MainLoop implements Runnable{
    private GameData gameData;

    public MainLoop(GameData gameData){
        this.gameData = new GameData();
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
            System.out.println("Current tik : " + gameData.tikManager.getCurrentTikTime());
            GameData.activeArea.setBlock(gameData.tikManager.getCurrentTikTime(),0, 0, 3);
            GameData.activeArea.setBlock(gameData.tikManager.getCurrentTikTime(),0, 1, 3);
            GameData.activeArea.setBlock(gameData.tikManager.getCurrentTikTime(),0, 2, 3);
            if (gameData.tikManager.getCurrentTikTime() % 2 == 0){
                GameData.activeArea.setBlock(gameData.tikManager.getCurrentTikTime(),0, 3, 3);
            }
        }
    }
}
