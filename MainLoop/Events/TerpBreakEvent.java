package MainLoop.Events;
import GameData.GameData;
import World.BlockEntity.Terp.Terp;

public class TerpBreakEvent implements Event{
    private GameData gameData;
    private Terp terp;
    private long blockKey;
    public TerpBreakEvent(GameData gameData, Terp terp, long blockKey){
        this.gameData = gameData;
        this.terp = terp;
        this.blockKey = blockKey;
    }

    @Override
    public boolean execute() {
        gameData.activeArea.setBlock(blockKey, 10);
        System.out.println("Block removed");
        return true;
    }
}
