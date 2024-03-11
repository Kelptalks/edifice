package GUI.IsoRenderer.Camera;

import GUI.IsoRenderer.Camera.GridManager.GridManager;
import GUI.IsoRenderer.Camera.GridManager.Structure.CastedBlockCuller;
import GUI.IsoRenderer.Camera.GridManager.Structure.CastedChunkManager;
import GUI.IsoRenderer.Camera.RayCaster.RayCaster;
import GUI.IsoRenderer.Camera.Gui.Window;
import GUI.IsoRenderer.Camera.RayCaster.Renderer;
import GUI.IsoRenderer.Textures.TextureManager;
import GameData.GameData;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Information for how the
 * camera will render the
 * image
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
public class CameraData {
    public CameraData(GameData gameData){
        this.castedBlockCuller = new CastedBlockCuller(gameData, this);
        this.rayCaster = new RayCaster(gameData, this);
        this.renderer = new Renderer(gameData, this);
    }
    public GridManager gridManager = new GridManager();

    public Renderer renderer;
    public RayCaster rayCaster;
    public TextureManager textureManager = new TextureManager(gridManager);
    public CastedBlockCuller castedBlockCuller;
    public CastedChunkManager castedChunkManager;
    public Window window;

    //Debug tools
    public boolean showChunkBoarders = false;

    public long camWorldKey = 576460750000000000L;

    //Measurements
    public int spriteXScale = 64;
    public int spriteYScale = spriteXScale/2;
    public int camXRez = 1920;
    public int camYRez = 1080;

    public int xChunkRez = 16;
    public int yChunkRez = 16;
    public int xChunkPixelRez = xChunkRez * spriteXScale + 64;
    public int yChunkPixelRez = yChunkRez * spriteYScale;
    public int xCamOffSet = 0;
    public int yCamOffSet = 0;
    public int camChunkXRez = 16;
    public int camChunkYRez = 32;
    public int camXCenterPixel = xChunkPixelRez * camChunkXRez / 2;
    public int camYCenterPixel = yChunkPixelRez * camChunkYRez / 4;

    //For rendering in different directions
    public int xCastingDirection = 1;
    public int yCastingDirection = 1;

}
