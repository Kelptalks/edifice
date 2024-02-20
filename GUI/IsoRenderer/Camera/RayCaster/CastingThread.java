package GUI.IsoRenderer.Camera.RayCaster;

import GUI.IsoRenderer.Camera.GridManager.Structure.CastedBlock;

public class CastingThread implements Runnable{
    private RayCaster rayCaster;
    CastedBlock castedBlock;

    CastingThread(RayCaster rayCaster, CastedBlock castedBlock){
        this.rayCaster = rayCaster;
        this.castedBlock = castedBlock;
    }
    @Override
    public void run() {
        castedBlock.setRightFilter(null);
        castedBlock.setLeftFilter(null);
        rayCaster.castLeft(castedBlock);
        rayCaster.castRight(castedBlock);
        rayCaster.castRightShadow(castedBlock);
        rayCaster.castLeftShadow(castedBlock);
    }
}
