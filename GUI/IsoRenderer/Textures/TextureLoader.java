package GUI.IsoRenderer.Textures;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TextureLoader {

    public Image loadAnimated(){
        return loadSpriteSheet("edifice/Textures/animated.png");
    }

    public Image loadSolidBlocks(){
        return loadSpriteSheet("edifice/Textures/solidBlocks.png");
    }

    //Sprite sheet loader
    public Image loadSpriteSheet(String filePath){
        try {
            Image spriteSheet = ImageIO.read(new File(filePath));
            System.out.println("Sprite sheet Loaded (" + filePath + ")");
            return spriteSheet;

        } catch (IOException e) {
            System.out.println("Failed to Load Textures");
            return null;
        }
    }
}
