package GUI.IsoRenderer.Camera;

import GUI.IsoRenderer.Camera.GridManager.GridManager;
import GUI.IsoRenderer.Camera.GridManager.Structure.CastedBlockCuller;
import GUI.IsoRenderer.Camera.GridManager.Structure.CastedChunkManager;
import GUI.IsoRenderer.Camera.RayCaster.RayCaster;
import GUI.IsoRenderer.Camera.Window.Window;
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
    }
    public GridManager gridManager = new GridManager();
    public RayCaster rayCaster;
    public TextureManager textureManager = new TextureManager(gridManager);
    public CastedBlockCuller castedBlockCuller;
    public CastedChunkManager castedChunkManager;
    public Window window;

    public long camWorldKey = 576460750000000000L;
    public int spriteXScale = 64;
    public int spriteYScale = spriteXScale/2;

    //Camera offsets
    public int camXRez = 1920;
    public int camYRez = 1080;

    public int xChunkRez = 16;
    public int yChunkRez = 16;
    public int xChunkPixelRez = xChunkRez * spriteXScale + 64;
    public int yChunkPixelRez = yChunkRez * spriteYScale;

    //Camera offsets
    public int xCamOffSet = 0;
    public int yCamOffSet = 0;

    public int camChunkXRez = 5;
    public int camChunkYRez = 10;

    public int camXCenterPixel = xChunkPixelRez * camChunkXRez / 2;
    public int camYCenterPixel = yChunkPixelRez * camChunkYRez / 4;

    //For rendering in different directions
    public int xCastingDirection = -1;
    public int yCastingDirection = 1;

}
