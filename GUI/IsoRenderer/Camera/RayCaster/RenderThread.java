package GUI.IsoRenderer.Camera.RayCaster;

import GUI.IsoRenderer.Camera.GridManager.Structure.CastedChunk;

public class RenderThread implements Runnable{
    private final RayCaster rayCaster;
    private final CastedChunk castedChunk;
    public RenderThread(RayCaster rayCaster, CastedChunk castedChunk){
        this.rayCaster = rayCaster;
        this.castedChunk = castedChunk;
    }

    @Override
    public void run() {
        rayCaster.castChunk(castedChunk);
    }
}
