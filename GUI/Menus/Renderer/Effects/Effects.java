package GUI.Menus.Renderer.Effects;

import GameData.GameData;

import java.awt.image.BufferedImage;

public class Effects extends BufferedImage {
    public Effects(GameData gameData){
        super(gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ, TYPE_4BYTE_ABGR_PRE);
    }

    public void drawLazer(int x, int y){
        
    }
}
