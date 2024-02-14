package GUI.IsoRenderer.Textures;

import GUI.IsoRenderer.Camera.GridManager.GridManager;
import GameData.Block;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NewTextureManager {
    private Image animations;
    private Image solidBlocks;
    private BufferedImage masks;
    private GridManager gridManager;

    public NewTextureManager(GridManager gridManager){
        TextureLoader textureLoader = new TextureLoader();
        this.solidBlocks = textureLoader.loadSolidBlocks();
        this.animations = textureLoader.loadAnimated();
        this.masks = (BufferedImage) textureLoader.loadmasks();
        this.gridManager = gridManager;

        for (Block block : Block.values()){
            for (Texture texture : Texture.values()){
                textures[block.id][texture.id] = getBlockSprite(block, texture);
            }
        }

        for (Texture texture : Texture.values()){
            filters[0][texture.id] = spliceTexture(0, texture, masks);
        }

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
        BufferedImage image = new BufferedImage(32, 32, BufferedImage.TYPE_4BYTE_ABGR);
        for (int y = 0; y < 32; y++){
            for (int x = 0; x < 32; x++){
                if((masks.getRGB(x, y + (64 * texture.mask))) != 0){
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

    private Image[][] filters = new Image[2][6];
    public Image getFilter(int filter, Texture texture){
        return filters[filter][texture.id];
    }

}
