package GUI.IsoRenderer.Camera;

import GUI.IsoRenderer.Camera.GridManager.GridManager;
import GUI.IsoRenderer.Camera.GridManager.Structure.CastedBlockCuller;
import GUI.IsoRenderer.Camera.RayCaster.RayCaster;
import GUI.IsoRenderer.Textures.NewTextureManager;
import GameData.GameData;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Information for how the
 * camera will render the
 * image
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
public class CameraData {
    public CameraData(GameData gameData){
        castedBlockCuller = new CastedBlockCuller(gameData);
    }

    public GridManager gridManager = new GridManager();
    public NewTextureManager textureManager = new NewTextureManager(gridManager);

    public CastedBlockCuller castedBlockCuller;

    public long camWorldKey = 576460750000000000L;
    public int spriteXScale = 64;
    public int spriteYScale = spriteXScale/2;

    //Camera offsets
    public int camXRez = 1920;
    public int camYRez = 1080;

    public int xCamCenter = camXRez/2;
    public int yCamCenter = camYRez/2;

    public int xChunkRez = 16;
    public int yChunkRez = 16;
    public int xChunkPixelRez = xChunkRez * spriteXScale + 64;
    public int yChunkPixelRez = yChunkRez * spriteYScale;

    //Camera offsets
    public int xCamOffSet = 0;
    public int yCamOffSet = 0;

    public int camChunkXRez = 10;
    public int camChunkYRez = 20;

    public int camXCenterPixel = xChunkPixelRez * camChunkXRez / 2;
    public int camYCenterPixel = yChunkPixelRez * camChunkYRez / 4;
}
