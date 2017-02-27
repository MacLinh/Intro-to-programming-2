import java.awt.*;
import javax.swing.*;

/**
 * In the application <b>FloodIt</b>, a <b>DotButton</b> is a specialized color of
 * <b>JButton</b> that represents a dot in the game. It can have one of six colors
 * 
 * The icon images are stored in a subdirectory ``data''. We have 3 sizes, ``normal'',
 * ``medium'' and ``small'', respectively in directory ``N'', ``M'' and ``S''.
 *
 * The images are 
 * ball-0.png => grey icon
 * ball-1.png => orange icon
 * ball-2.png => blue icon
 * ball-3.png => green icon
 * ball-4.png => purple icon
 * ball-5.png => red icon
 *
 *  <a href=
 * "http://developer.apple.com/library/safari/#samplecode/Puzzler/Introduction/Intro.html%23//apple_ref/doc/uid/DTS10004409"
 * >Based on Puzzler by Apple</a>.
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class DotButton extends JButton {
  
  public static final int SMALL_SIZE = 15, MEDIUM_SIZE = 30, NORMAL_SIZE = 50;
  /**
   * the color and size of the button
   */
  private int color;

  /**
   * the different icons available
   */
  private static ImageIcon[] icons; // this is static so its only loaded once to save memory and time
  
  /**
   * the current icon size of all buttons
   */
  private static int iconSize;
    /**
     * Constructor used for initializing a cell of a specified color.
     * 
     * @param row
     *            the row of this Cell
     * @param column
     *            the column of this Cell
     * @param color
     *            specifies the color of this cell
     * @param iconSize
     *            specifies the size to use, one of SMALL_SIZE, MEDIUM_SIZE or MEDIUM_SIZE
     */

    public DotButton(int row, int column, int color, int iconSize) {

// ADD YOUR CODE HERE

   }

 /**
     * Other constructor used for initializing a cell of a specified color.
     * no row or column info available. Uses "-1, -1" for row and column instead
     * 
     * @param color
     *            specifies the color of this cell
     * @param iconSize
     *            specifies the size to use, one of SMALL_SIZE, MEDIUM_SIZE or MEDIUM_SIZE
     */   
    public DotButton(int color, int iconSize) {
      this.color = color;
      setPreferredSize(new Dimension(iconSize,iconSize));
      
      if(this.iconSize != iconSize){
        this.iconSize = iconSize;
        loadImages();
      }
      setIcon(icons[color]);
      setBackground(Color.white);
      setActionCommand(""+color);
    }
    
    /**
     * loads all the image files. Note: 
     */
    private static void loadImages(){
      icons = new ImageIcon[GameModel.NUMBER_OF_COLORS];
      
      String folder;
      if(iconSize == SMALL_SIZE)
        folder = "S";
      else if(iconSize == MEDIUM_SIZE)
        folder = "M";
      else if(iconSize == NORMAL_SIZE)
        folder = "N";
      else
        throw new IllegalArgumentException("icon size doesn't exist");
      
      for(int i = 0; i < icons.length; i++){
        icons[i] = new ImageIcon("data/"+folder+"/ball-"+i+".png");
      }
    }
    
    /**
     * Changes the cell color of this cell. The image is updated accordingly.
     * 
     * @param color
     *            the color to set
     */

    public void setColor(int color) {
      this.color = color;
      setIcon(icons[color]);
      setActionCommand(""+color);
   }

    /**
     * Getter for color
     *
     * @return color
     */
    public int getColor(){
      return color;
    }
 
    /**
     * Getter method for the attribute row.
     * 
     * @return the value of the attribute row
     */

    public int getRow() {
      return 0;
    }

    /**
     * Getter method for the attribute column.
     * 
     * @return the value of the attribute column
     */

    public int getColumn() {
      return 0;
    }


// ADD YOUR PRIVATE METHODS HERE (IF USING ANY)


}