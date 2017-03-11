// Authors: Aleeza Ladhani, Mac Linh Pham
// Student numbers: 8195730, 8703691
// Course: ITI 1121-C
// Assignment: 2

/**
 * The class <b>DotInfo</b> is a simple helper class to store the initial color and state
 * (captured or not) at the dot position (x,y)
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class DotInfo {
    
    /**
     * number of captured dots; when value reaches the number of total dots, the game is over
     */
    private static int numCaptured = 0;
    
    /**
     * the x and y coordinate of the dot
     */
    private int x, y;
    
    /**
     * the color of the dot
     */
    private int color;
    
    /**
     * whether or not the dot has been captured by the player
     */
    private boolean captured;
    
    
    /**
     * Constructor 
     * 
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     * @param color
     *            the initial color
     */
    public DotInfo(int x, int y, int color){
        this.x = x;
        this.y = y;
        this.color = color;
        captured = false;
    }
    
    
    /**
     * sets the current captured dots
     */
    public static void setNumberCaptured(int num){
        numCaptured = num;
    }
    
    /**
     * gets the number of captured dots
     */
    public static int getNumberCaptured(){
        return numCaptured;
    }
    /**
     * Getter method for the attribute x.
     * 
     * @return the value of the attribute x
     */
    public int getX(){
        return x;
    }
    
    /**
     * Getter method for the attribute y.
     * 
     * @return the value of the attribute y
     */
    public int getY(){
        return y;
    }
    
    
    /**
     * Setter for captured
     * @param captured
     *            the new value for captured
     */
    public void setCaptured(boolean captured) {
        if(captured)
            numCaptured++;
        this.captured = captured;
    }
    
    /**
     * Get for captured
     *
     * @return captured
     */
    public boolean isCaptured(){
        return captured;
    }
    
    /**
     * Get for color
     *
     * @return color
     */
    public int getColor() {
        return color;
    }
    
    /**
     * sets the color\
     */
    public void setColor(int color) {
        this.color = color;
    }
    
    /**
     * tests if it is equal to another dot  - i.e. is it the same color?
     * 
     * @return returns if same color
     */
    @Override
    public boolean equals(Object ref){
        if(!(ref instanceof DotInfo))
            return false;
        DotInfo other = (DotInfo) ref;
        return color == other.getColor();
    }
}