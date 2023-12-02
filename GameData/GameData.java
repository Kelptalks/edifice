package GameData;

import MainLoop.TikManager.TikManager;
import World.ActiveBranch.ActiveArea;
import World.Octree.Octree;
import World.World;

public class GameData {
    public long playerXCamCor = 0;
    public long playerYCamCor = 0;
    public long playerZCamCor = 0;

    public int currentBlock = 1;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Render Settings
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public int SCREEN_X_REZ = 1920;
    public int SCREEN_Y_REZ = 1080;
    public int drawDistance = 150;
    public int xCamRez = 31;
    public int yCamRez = 70;

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
