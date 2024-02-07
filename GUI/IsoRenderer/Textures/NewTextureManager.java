package GUI.IsoRenderer.Textures;

import GUI.IsoRenderer.GridManager.GridManager;
import GameData.Block;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NewTextureManager {
    private Image animations;
    private Image solidBlocks;
    private GridManager gridManager;

    public NewTextureManager(GridManager gridManager){
        TextureLoader textureLoader = new TextureLoader();
        this.solidBlocks = textureLoader.loadSolidBlocks();
        this.animations = textureLoader.loadAnimated();
        this.gridManager = gridManager;

        for (Block block : Block.values()){
            for (Texture texture : Texture.values()){
                getBlockSprite(block, texture);
            }
        }
    }

    public Image getBlockSprite(Block block, Texture texture){
        if (block.spriteSheet == "solidBlocks"){
            return spliceTexture(block, texture, solidBlocks);
        }
        else if (block.spriteSheet == "animated"){
            return spliceTexture(block, texture, animations);
        }
        return null;
    }

    public Image spliceTexture(Block block, Texture texture, Image spriteSheet){
        BufferedImage image = new BufferedImage(32, 32, BufferedImage.TYPE_4BYTE_ABGR);

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        if (texture.leftFacing == true){
            graphics.setClip(gridManager.getLeftTriangle(0, 0, 16));
        }
        else{
            graphics.setClip(gridManager.getRightTriangle(0, 0, 16));
        }
        graphics.drawImage(spriteSheet, texture.translations[0], texture.translations[1] + (-64 * block.spriteSheetIndex), null);

        textures[block.id][texture.id] = image;
        return image;
    }

    private Image[][] textures = new Image[Block.values().length][6];
    public Image getTexture(Block block, Texture texture){
        return textures[block.id][texture.id];
    }

}
