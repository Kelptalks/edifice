package World.BlockEntity.Terp;

import GameData.GameData;
import World.BlockEntity.BlockEntity;

public class Terp implements BlockEntity {

    private long location;
    public Terp(long location){
        this.location = location;
        TerpManager terpManager = new TerpManager(this);
        System.out.println("terp created");
        breakBlock(4611686018427387903L);
    }

    public long getLocation(){
        return this.location;
    }

    @Override
    public String getType() {
        return "Terp";
    }

    public int getTexture(){
        return 11;
    }

    public void breakBlock(long key){
        GameData.activeArea.setBlock(key, 0);
    }
}
