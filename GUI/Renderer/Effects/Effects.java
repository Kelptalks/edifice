package GUI.Renderer.Effects;

import GameData.GameData;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Effects extends BufferedImage {

    private Graphics2D g2d = this.createGraphics();

    public Effects(GameData gameData){
        super(gameData.SCREEN_X_REZ, gameData.SCREEN_Y_REZ, TYPE_4BYTE_ABGR_PRE);
    }

    public void drawLazer(int x, int y){
        
    }

    public void drawPoint(int x, int y){
        clear();

        g2d.setColor(Color.white);

        Polygon topFace = new Polygon();
        x-=32;
        y-=16;
        topFace.addPoint(x + 0, y + 16);
        topFace.addPoint(x + 32, y + 0);
        topFace.addPoint(x + 64, y + 16);
        topFace.addPoint(x + 32, y + 32);

        g2d.drawPolygon(topFace);
    }

    public void clear(){
        // Enable antialiasing for better quality
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Clear the image with a transparent rectangle
        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setComposite(AlphaComposite.SrcOver);
    }
}
