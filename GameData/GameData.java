package GameData;

import MainLoop.TikManager.TikManager;
import World.ActiveBranch.ActiveArea;
import World.Octree.KeyMod;
import World.World;

public class GameData {
    public long playerCamKey = 576460750000000000L;

    public int canvasXOffSet = 0;
    public int canvasYOffSet = 0;
    public KeyMod keyMod = new KeyMod();

    public Block[] blocks = Block.values();
    public Block currentBlock = blocks[1];

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Render Settings
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public int SCREEN_X_REZ = 1920;
    public int SCREEN_Y_REZ = 1080;
    public int drawDistance = 200;
    public int xCamRez = SCREEN_X_REZ/64 * 2;
    public int yCamRez = SCREEN_Y_REZ/16 * 2;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Game state
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public long worldCenter = 576460750000000000L;
    public TikManager tikManager = new TikManager();

    public World world;
    public ActiveArea activeArea;

    //set up the world info
    public void setUpWorld(){
        this.world = new World(this);
        activeArea = new ActiveArea(world, worldCenter, 7);
    }
}
