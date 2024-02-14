package GUI.IsoRenderer.Textures;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TextureLoader {

    public Image loadAnimated(){
        return loadSpriteSheet("Textures/animated.png");
    }

    public Image loadSolidBlocks(){
        return loadSpriteSheet("Textures/solidBlocks.png");
    }

    public Image loadMasks() {
        return loadSpriteSheet("Textures/Masks.png");
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

    public Image loadFilters() {
        return loadSpriteSheet("Textures/filters.png");
    }
}
