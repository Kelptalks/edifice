package GameData;

import GUI.Menus.Menu;
import MainLoop.TikManager.TikManager;
import World.ActiveBranch.ActiveArea;
import World.Octree.Octree;
import World.World;

public class GameData {
    public long playerXCamCor = 0;
    public long playerYCamCor = 0;
    public long playerZCamCor = 0;
    public TikManager tikManager = new TikManager();

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Menu
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public Menu menu;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Render Settings
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public int SCREEN_X_REZ = 1920;
    public int SCREEN_Y_REZ = 1080;
    public int drawDistance = 150;
    public int xCamRez = 29;
    public int yCamRez = 61;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  World
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public static World world = new World();
    public static ActiveArea activeArea = new ActiveArea(world, 576460750000000000L, 7);
    public String SaveDirectory = "World/DataStorage/Octree/SaveData";
    public int currentBlock = 1;
}
