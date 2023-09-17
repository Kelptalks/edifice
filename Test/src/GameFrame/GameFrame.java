package GameFrame;
import javax.swing.JFrame;
/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Rendering
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Interface for higher level block rendering
 * 
 */

public class GameFrame extends JFrame{

    public BlockRenderingLabel gameLabel;
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Constructor
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public GameFrame(){
        super();
        this.setSize(1920, 1080);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Create and add the Block renderer
        this.gameLabel = new BlockRenderingLabel();

        this.add(gameLabel);
        this.setVisible(true);
    }

}
