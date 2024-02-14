package GUI.IsoRenderer.Textures;

import GameData.Block;

import java.awt.*;
import java.awt.image.BufferedImage;

public enum Texture {
    LeftTopFace(0, new int[]{0, -16}, false, 0),
    LeftBotFace(1, new int[]{0, -32}, true, 3),
    TopLeftFace(2, new int[]{0, 0}, true, 2),
    TopRightFace(3, new int[]{-32, 0}, false, 1),
    RightTopFace(4, new int[]{-32, -16}, true, 4),
    RightBotFace(5, new int[]{-32, -32}, false, 5);

    int id;
    public final int[] translations;
    public boolean leftFacing;
    public int mask;
    Texture(int id, int[] translations, boolean leftFacing, int mask){
        this.id = id;
        this.translations = translations;
        this.leftFacing = leftFacing;
    }
}
