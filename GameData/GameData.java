package GameData;

import GUI.Menus.Menu;
import World.DataStorage.Octree.Octree;

public class GameData {

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

    //draw distance
    public int drawDistance = 150;

    //Render Size
    public int xCamRez = 29;
    public int yCamRez = 61;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  World
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */

    public Octree octree;



    public String SaveDirectory = "World/DataStorage/Octree/SaveData";

}
