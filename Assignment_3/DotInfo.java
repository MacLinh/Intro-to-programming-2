
/**
 * The class <b>DotInfo</b> is a simple helper class to store the initial color and state
 * (captured or not) at the dot position (x,y)
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class DotInfo {
  
  /**
   * the x and y coordinate of the dot
   */
  private int x, y;
  
  /**
   * the color of the dot
   */
  private int color;
  
  /**
   * whether or not the dot has been captured by player
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
  
  @Override
  public boolean equals(Object ref){
    DotButton other = (DotButton) ref;
    return color == other.getColor();
  }
}