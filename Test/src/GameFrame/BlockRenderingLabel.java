package GameFrame;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

public class BlockRenderingLabel extends JLabel{
    private BlockRender blockRender;

    public BlockRenderingLabel() {
        this.blockRender = new BlockRender();
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(blockRender, 0, 0, null);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Debugging Methods
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    */

    public void depthDrawChunk(int[] chunkData, int xCor, int yCor){
        blockRender.depthDrawChunk(chunkData, xCor, yCor);
        this.repaint();
    }

    
}