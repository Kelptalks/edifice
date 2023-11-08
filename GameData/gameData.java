package GameData;

import GUI.Menus.Menu;

public class gameData {

    public long playerXCamCor = 0;
    public long playerYCamCor = 0;
    public long playerZCamCor = 0;

    public boolean block = false;

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
