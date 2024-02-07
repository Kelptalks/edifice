package GUI.IsoRenderer;

import GUI.IsoRenderer.GridManager.CastedBlock;
import GUI.IsoRenderer.GridManager.CastedBlockCuller;
import GUI.IsoRenderer.GridManager.GridManager;
import GUI.IsoRenderer.RayCaster.RayCaster;
import GUI.IsoRenderer.Textures.NewTextureManager;
import GUI.IsoRenderer.Textures.Texture;
import GameData.Block;
import GameData.GameData;

import javax.swing.plaf.synth.SynthStyle;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Canvas extends BufferedImage {


    private final GameData gameData;
    private Graphics graphics; //Drawing tool from awt
    private GridManager gridManager;
    private NewTextureManager textureManager;
    private RayCaster rayCaster;
    private CastedBlockCuller castedBlockCuller;
    private CastedBlock[][] castedBlocks;
    public Canvas(GameData gameData, int xRez, int yRez) {
        super(xRez, yRez, TYPE_4BYTE_ABGR_PRE);
        this.gameData = gameData;
        this.graphics = super.getGraphics();
        this.gridManager = new GridManager();
        this.textureManager = new NewTextureManager(gridManager);
        this.rayCaster = new RayCaster(gameData);
        this.castedBlockCuller = new CastedBlockCuller(gameData);
        this.castedBlocks = castedBlockCuller.getCulledCordMods(gameData.xCamRez, gameData.yCamRez);


    }


    //Renders a frame
    private int temp = 1;
    public void renderFrame() {

        //Clear the frame
        graphics.setColor(Color.CYAN);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());

        long t1 = System.nanoTime();
        rayCaster.castBlocks(castedBlocks);


        drawCastedBlocks(castedBlocks);
        long t2 = System.nanoTime();

        this.castedBlocks = castedBlockCuller.getCulledCordMods(gameData.xCamRez, gameData.yCamRez);

        gameData.playerCamKey = gameData.keyMod.getRelativeKey(gameData.playerCamKey, 0, 0, 0, 0);

        Image tmp = this.getScaledInstance(1920, 1080 - temp, Image.SCALE_SMOOTH);

        temp++;
        System.out.println((t2 - t1) / 1000000);

    }


    public void drawCastedBlocks(CastedBlock[][] castedBlocks){
        for (CastedBlock[] castedBlockRow : castedBlocks){
            for (CastedBlock castedBlock : castedBlockRow){
                drawCastedBlock(castedBlock);
            }
        }
    }

    public void drawCastedBlock(CastedBlock castedBlock){
        Block[] blockType = castedBlock.getBlockType();
        int[] drawCords = gridManager.IsoToScreen(castedBlock.getIsoScreenCords());

        Image leftTri = textureManager.getTexture(blockType[0], castedBlock.getLeftTexture());
        Image rightTri = textureManager.getTexture(blockType[1], castedBlock.getRightTexture());

        graphics.drawImage(leftTri, drawCords[0], drawCords[1], null);
        graphics.drawImage(rightTri, drawCords[0] + 32, drawCords[1], null);
    }

    public void drawPoint(int xCor, int yCor){
        graphics.setColor(Color.RED);
        graphics.fillOval(xCor, yCor, 5, 5);
        String cords = "(" + xCor + ", " + yCor + ")";
        graphics.drawString(cords, xCor + 3, yCor);
    }
}
