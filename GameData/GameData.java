package GameData;

import GUI.Menus.Main.Main;
import GUI.Menus.Menu;
import World.DataStorage.Octree.ActiveBranch;
import World.DataStorage.Octree.Octree;

public class GameData {

    public static long playerXCamCor = 0;
    public static long playerYCamCor = 0;
    public static long playerZCamCor = 0;

    public static boolean block = false;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Menu
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public Menu menu;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Screen rez
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public int SCREEN_X_REZ = 1920;
    public int SCREEN_Y_REZ = 1080;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  World
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */

    public String SaveDirectory = "World/DataStorage/Octree/SaveData";
}
