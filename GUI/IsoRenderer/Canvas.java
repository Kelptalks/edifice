package GUI.IsoRenderer;

import GUI.IsoRenderer.Camera.GridManager.Structure.CastedBlock;
import GUI.IsoRenderer.Camera.GridManager.GridManager;
import GUI.IsoRenderer.Textures.NewTextureManager;
import GameData.Block;
import GameData.GameData;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Canvas extends BufferedImage {


    private final GameData gameData;
    private final GridManager gridManager;
    private Graphics graphics; //Drawing tool from awt
    private NewTextureManager textureManager;
    public Canvas(GameData gameData, GridManager gridManager, int xRez, int yRez) {
        super(xRez, yRez, TYPE_4BYTE_ABGR_PRE);
        this.gameData = gameData;

        this.graphics = super.getGraphics();
        this.gridManager = gridManager;
        this.textureManager = new NewTextureManager(gridManager);
    }


    //Renders a frame
    public void renderFrame(CastedBlock[][] castedBlocks) {
        //Clear the frame
        graphics.setColor(Color.CYAN);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        drawCastedBlocks(castedBlocks);
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
        int[] drawCords = gridManager.isoToScreen(castedBlock.getIsoScreenCords());

        Image leftTri = textureManager.getTexture(blockType[0], castedBlock.getLeftTexture());
        Image rightTri = textureManager.getTexture(blockType[1], castedBlock.getRightTexture());

        graphics.drawImage(leftTri, drawCords[0], drawCords[1], null);
        graphics.drawImage(rightTri, drawCords[0] + 32, drawCords[1], null);


        graphics.setColor(Color.WHITE);

        if ((inputScreenCor[0] / 32)%2 == 0){
            graphics.drawPolygon(gridManager.getLeftTriangle(screenCor[0], screenCor[1]));
        }
        else {
            graphics.drawPolygon(gridManager.getRightTriangle(screenCor[0] + 32, screenCor[1]));
        }
    }

    private int[] screenCor = new int[]{0, 0};
    private int[] inputScreenCor = new int[]{0, 0};
    public void test(int x, int y) {
        inputScreenCor = new int[]{x, y};
        int[] isoCords = gridManager.ScreenToIso(x, y);
        this.screenCor = gridManager.isoToScreen(isoCords);
    }

    public void drawPoint(int xCor, int yCor) {
        graphics.fillOval(xCor, yCor, 5, 5);

        int[] iso = gridManager.ScreenToIso(xCor, yCor);

        String cords = "(" + iso[0] + ", " + iso[1] + ")";
        graphics.drawString(cords, xCor + 3, yCor);
    }
}
