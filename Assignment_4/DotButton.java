// Authors: Aleeza Ladhani, Mac Linh Pham
// Student numbers: 8195730, 8703691
// Course: ITI 1121-C
// Assignment: 2

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
    
    /**
     * the smallest sized icon
     */
    public static final int SMALL_SIZE = 15; 
    
    /**
     * the medium sized icon
     */
    public static final int MEDIUM_SIZE = 30;
    
    /**
     * the largest sized icon
     */
    public static final int NORMAL_SIZE = 40;
    
    /**
     * the different icons available
     */
    public static ImageIcon[][] icons; // this is static so its only loaded once to save memory and time
    
    /**
     * the color and size of the button
     */
    private int color;
    
    /**
     * the size of the button icon
     */
    private int type;
    
    /**
     * the number of the button
     */
    private int number;
    
    /**
     * the total number of buttons
     */
    private static int total;
    
    /*
     * initializes the all the icon images
     */
    static{
        icons = new ImageIcon[3][GameModel.NUMBER_OF_COLORS];
        for(int i = 0; i < GameModel.NUMBER_OF_COLORS; i++){
            icons[0][i] = new ImageIcon("data/S/ball-"+i+".png");
        }
        for(int i = 0; i < GameModel.NUMBER_OF_COLORS; i++){
            icons[1][i] = new ImageIcon("data/M/ball-"+i+".png");
        }
        for(int i = 0; i < GameModel.NUMBER_OF_COLORS; i++){
            icons[2][i] = new ImageIcon("data/N/ball-"+i+".png");
        }
        total = 0;
    }
    
    
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
     *            specifies the size to use, one of SMALL_SIZE, MEDIUM_SIZE or NORMAL_SIZE
     */
    public DotButton(int row, int column, int color, int iconSize) {
        // This is unused
        this(color,iconSize);
    }
    
    
    /** 
     * Other constructor used for initializing a cell of a specified color.
     * no row or column info available. Uses "-1, -1" for row and column instead
     * 
     * @param color
     *            specifies the color of this cell
     * @param iconSize
     *            specifies the size to use, one of SMALL_SIZE, MEDIUM_SIZE or NORMAL_SIZE
     */   
    public DotButton(int color, int iconSize) {
        this.color = color;
        setPreferredSize(new Dimension(iconSize,iconSize));
        setBorder(BorderFactory.createEmptyBorder());
        switch(iconSize){
            case SMALL_SIZE :
                type = 0;
                break;
            case MEDIUM_SIZE :
                type = 1;
                break;
            case NORMAL_SIZE :
                type = 2;
                break;
            default:
                throw new IllegalArgumentException("the icon size "+iconSize+" does not exist");
                
        }
        setActionCommand(""+color);
        setIcon(icons[type][color]);
        setBackground(Color.black);
        number = total++;
    }
    
    
    /**
     * Changes the cell color of this cell. The image is updated accordingly.
     * 
     * @param color
     *            the color to set
     */
    
    public synchronized void setColor(int color) {
        this.color = color;
        setActionCommand(""+color);
        setIcon(icons[type][color]);
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
        int size = (int) Math.sqrt(total); // will not work if it is not a perfect square grid
        return (int)number/size;
    }
    
    /**
     * Getter method for the attribute column.
     * 
     * @return the value of the attribute column
     */
    
    public int getColumn() {
        int size = (int) Math.sqrt(total);
        return number%size;
    }
    
}