package GUI.IsoRenderer.Textures;

import GUI.IsoRenderer.Camera.GridManager.GridManager;
import GameData.Block;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class NewTextureManager {
    private Image animations;
    private Image solidBlocks;
    private Image filters;
    private BufferedImage masks;
    private GridManager gridManager;

    public NewTextureManager(GridManager gridManager){
        //Load all the required sprite sheets
        TextureLoader textureLoader = new TextureLoader();
        this.solidBlocks = textureLoader.loadSolidBlocks();
        this.animations = textureLoader.loadAnimated();
        this.masks = (BufferedImage) textureLoader.loadMasks();
        this.filters =  textureLoader.loadFilters();
        this.gridManager = gridManager;


        Texture[] faceTextures =  Arrays.copyOfRange(Texture.values(), 0 , 6);

        //Splice and store textures for every block type
        for (Block block : Block.values()){
            for (Texture texture : faceTextures){
                textures[block.id][texture.id] = getBlockSprite(block, texture);
            }
        }

        //Splice and store shaders
        for (Texture texture : Texture.values()){
            shaders[0][texture.id] = maskTexture(0, texture, (BufferedImage) filters);
        }

        System.out.println(masks.getHeight());
        System.out.println(masks.getRGB(16, 64 + 24));
        System.out.println(masks.getRGB(20, 64 + 24));

    }

    public Image getBlockSprite(Block block, Texture texture){
        if (block.spriteSheet == "solidBlocks"){
            return spliceTexture(block.spriteSheetIndex, texture, solidBlocks);
        }
        else if (block.spriteSheet == "animated"){
            return spliceTexture(block.spriteSheetIndex, texture, animations);
        }
        return null;
    }

    public Image spliceTexture(int spriteSheetIndex, Texture texture, Image spriteSheet){
        BufferedImage image = new BufferedImage(32, 32, BufferedImage.TYPE_4BYTE_ABGR);

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        if (texture.leftFacing){
            graphics.setClip(gridManager.getLeftTriangle(0, 0));
        }
        else{
            graphics.setClip(gridManager.getRightTriangle(0, 0));
        }
        graphics.drawImage(spriteSheet, texture.translations[0], texture.translations[1] + (-64 * spriteSheetIndex), null);
        return image;
    }

    public Image maskTexture(int spriteSheetIndex, Texture texture, BufferedImage spriteSheet){
        BufferedImage image = new BufferedImage(64, 64, BufferedImage.TYPE_4BYTE_ABGR);
        for (int y = 0; y < 64; y++){
            for (int x = 0; x < 64; x++){
                if((masks.getRGB(x, y + (64 * texture.maskIndex))) == texture.maskRGB){
                    image.setRGB(x, y, spriteSheet.getRGB(x, y + (64 * spriteSheetIndex)));
                }

            }
        }
        return image;
    }

    private Image[][] textures = new Image[Block.values().length][6];
    public Image getTexture(Block block, Texture texture){
        return textures[block.id][texture.id];
    }

    private Image[][] shaders = new Image[1][Texture.values().length];
    public Image getFilter(int filter, Texture texture){
        return shaders[filter][texture.id];
    }

}
