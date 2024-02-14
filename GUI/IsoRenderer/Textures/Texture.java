package GUI.IsoRenderer.Textures;

import GameData.Block;

import java.awt.*;
import java.awt.image.BufferedImage;

public enum Texture {
    LeftTopFace(0, new int[]{0, -16}, false, 0, -65536),
    LeftBotFace(1, new int[]{0, -32}, true, 0, -196376),
    //16727553
    TopLeftFace(2, new int[]{0, 0}, true, 0, -786688),
    //-196376
    TopRightFace(3, new int[]{-32, 0}, false, 0, -16253184),
    RightTopFace(4, new int[]{-32, -16}, true, 0, -16727553),
    //-3801344
    RightBotFace(5, new int[]{-32, -32}, false, 0, -9568001),


    //Shading
    TopTopHalf(6, new int[]{0, 0}, false, 1, -65536),
    BotBotHalf(7, new int[]{0, 0}, false, 1, -3801344),
    LeftFaceNorth(8, new int[]{0, 0}, false, 1, -8257281),
    LeftFaceEast(9, new int[]{0, 0}, false, 1, -3801344),
    LeftFaceSouth(8, new int[]{0, 0}, false, 1, -16727553),
    LeftFaceWest(9, new int[]{0, 0}, false, 1, -16253184);

    int id;
    public final int[] translations;
    public boolean leftFacing;
    public int maskIndex;
    public int maskRGB;
    Texture(int id, int[] translations, boolean leftFacing, int maskIndex, int maskRGB){
        this.id = id;
        this.translations = translations;
        this.leftFacing = leftFacing;
        this.maskIndex = maskIndex;
        this.maskRGB = maskRGB;
    }
}
