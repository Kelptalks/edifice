package World.BlockEntity.Terp;

import GameData.GameData;
import MainLoop.Events.TerpBreakEvent;
import World.BlockEntity.BlockEntity;
import World.Octree.KeyMod;

public class Terp implements BlockEntity {

    private long location;
    private GameData gameData;
    private KeyMod keyMod =  new KeyMod();

    int inventory = 0;
    public Terp(GameData gameData, long location){
        this.location = location;
        this.gameData = gameData;
        TerpManager terpManager = new TerpManager(this);
        System.out.println("terp created");
        //breakBlock(4611686018427387903L, 5);
        clearArea(new int[]{20, 20, 20}, 4611686018427387903L);
    }

    public long getLocation(){
        return this.location;
    }

    private void breakBlock(long blockKey, int ticTime){
        //schedule the terps block breakage
        gameData.tikManager.addEvent((int) (gameData.tikManager.getCurrentTikTime() + ticTime),
                new TerpBreakEvent(gameData, this, blockKey));
    }



    public void setInventory(int block){
        this.inventory = block;
    }

    private void clearArea(int[] dimentions, long locationKey){
        int time = 0;
        for(int z = 0; z < dimentions[2]; z++){
            for (int y = 0; y < dimentions[1]; y++){
                for (int x = 0; x < dimentions[0]; x++){
                    time++;
                    breakBlock(keyMod.getRelativeKey(locationKey, 0, x, y, z), time);
                }
            }
        }
    }

    private void buildArea(int[][][] blueprint, long locationKey){

    }
}
