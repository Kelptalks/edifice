package GUI.IsoRenderer.Textures;

import GameData.Block;

import java.awt.*;
import java.awt.image.BufferedImage;

public enum Texture {
    LeftTopFace(0, new int[]{0, -16}, false),
    LeftBotFace(1, new int[]{0, -32}, true),
    TopLeftFace(2, new int[]{0, 0}, true),
    TopRightFace(3, new int[]{-32, 0}, false),
    RightTopFace(4, new int[]{-32, -16}, true),
    RightBotFace(5, new int[]{-32, -32}, false);

    int id;
    public final int[] translations;
    public boolean leftFacing;
    Texture(int id, int[] translations, boolean leftFacing){
        this.id = id;
        this.translations = translations;
        this.leftFacing = leftFacing;
    }
}
