// Authors: Aleeza Ladhani, Mac Linh Pham
// Student numbers: 8195730, 8703691
// Course: ITI 1121-C
// Assignment: 2

import java.util.Random;
import java.io.*;

/**
 * The class <b>GameModel</b> holds the model, the state of the systems. 
 * It stores the followiung information:
 * - the state of all the ``dots'' on the board (color, captured or not)
 * - the size of the board
 * - the number of steps since the last reset
 * - the current color of selection
 *
 * The model provides all of this informations to the other classes trough 
 *  appropriate Getters. 
 * The controller can also update the model through Setters.
 * Finally, the model is also in charge of initializing the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class GameModel implements Serializable, Cloneable{
  
  /**
   * predefined values to capture the color of a DotInfo
   */
  public static final int COLOR_0           = 0;
  public static final int COLOR_1           = 1;
  public static final int COLOR_2           = 2;
  public static final int COLOR_3           = 3;
  public static final int COLOR_4           = 4;
  public static final int COLOR_5           = 5;
  public static final int NUMBER_OF_COLORS  = 6;
  
  /**
   * the size of the square board
   */
  private int size;
  
  /**
   * all the dots on the board
   */
  private DotInfo[] dots;
  
  
  /**
   * the number of steps taken so far by the player
   */
  private int steps;
  
  /**
   * number of captured dots; when value reaches the number of total dots, the game is over
   */
  private int numCaptured = 0;
  
  /**
   * the current color selected by the player
   */
  private int selectedColor;
  /**
   * to generate the random colors
   */
  private static Random random;
  
  /**
   * stores the history of GameModels for the undo feature
   */
  private static LinkedStack<GameModel> history;
  
  /**
   * playing on (plane or torus) or (orthogonal or diagonal)
   */
  private boolean isTorus, isDiagonal;
  
  /*
   * initializes static fields
   */
  static{
    random = new Random();
    history = new LinkedStack<GameModel>();
  }
  /**
   * Constructor to initialize the model to a given size of board.
   * 
   * @param size
   *            the size of the board
   */
  public GameModel(int size) {
    this.size = size;
    dots = new DotInfo[size*size];
    steps = -1;
    System.out.println("steps =" + steps);
    isTorus = true;
    isDiagonal = false;
    reset();
  }
  
  
  /**
   * Resets the model to (re)start a game. The previous game (if there is one)
   * is cleared up . 
   */
  public void reset(){
    numCaptured = 0;
    steps = -1;
    for(int i = 0; i < size*size; i++){
      dots[i] = new DotInfo(i%size,(int)i/size,random.nextInt(NUMBER_OF_COLORS));
    }
  }
  
  
  /**
   * Getter method for the size of the game
   * 
   * @return the value of the attribute sizeOfGame
   */   
  public int getSize(){
    return size;
  }
  
  /**
   * returns the current color of a given dot in the game
   * 
   * @param i
   *            the x coordinate of the dot
   * @param j
   *            the y coordinate of the dot
   * @return the status of the dot at location (i,j)
   */   
  public int getColor(int i, int j){
    return getColor(j*size + i);
  }
  
  /**
   * returns the current color of a given dot in the game
   * 
   * @param x the index of the dot
   */
  public int getColor(int x){
    return dots[x].getColor(); // for purpose of updating grid - we don't care about coordinates
  }
  
  /**
   * returns true is the dot is captured, false otherwise
   * 
   * @param i
   *            the x coordinate of the dot
   * @param j
   *            the y coordinate of the dot
   * @return the status of the dot at location (i,j)
   */   
  public boolean isCaptured(int i, int j){
    return dots[j*size + i].isCaptured();
  }
  
  /**
   * returns true is the dot is captured, false otherwise
   * 
   * @param x
   *            the position coordinate of the dot
   *
   * @return the status of the dot at location on array
   */  
  public boolean isCaptured(int x){
    return dots[x].isCaptured();
  }
  
  /**
   * Sets the status of the dot at coordinate (i,j) to captured
   * 
   * @param i
   *            the x coordinate of the dot
   * @param j
   *            the y coordinate of the dot
   */   
  public void capture(int i, int j){
    dots[j*size + i].setCaptured(true);
  }
  
  
  /**
   * Getter method for the current number of steps
   * 
   * @return the current number of steps
   */   
  public int getNumberOfSteps(){
    return steps;
  }
  
  /**
   * Setter method for currentSelectedColor
   * 
   * @param val
   *            the new value for currentSelectedColor
   */   
  public void setCurrentSelectedColor(int val) {
    selectedColor = val;
  }
  
  /**
   * Getter method for currentSelectedColor
   * 
   * @return currentSelectedColor
   */   
  public int getCurrentSelectedColor() {
    return selectedColor;
  }
  
  
  /**
   * Getter method for the model's dotInfo reference
   * at location (i,j)
   *
   * @param i
   *            the x coordinate of the dot
   * @param j
   *            the y coordinate of the dot
   *
   * @return model[i][j]
   */   
  public DotInfo get(int i, int j) {
    return dots[j*size + i];
  }
  
  /**
   * @return the array of dots
   */
  public DotInfo[] getDots(){
    return dots;
  }
  /**
   * The metod <b>step</b> updates the number of steps. It must be called 
   * once the model has been updated after the payer selected a new color.
   */
  public void step(){
    steps++;
  }
  
  /**
   * when a new dot is captured this is tallied to keep track of victory
   */
  public void progress(){
    numCaptured++;
  }
  
  /**
   * The metod <b>isFinished</b> returns true iff the game is finished, that
   * is, all the dats are captured.
   *
   * @return true if the game is finished, false otherwise
   */
  public boolean isFinished(){
    return(numCaptured == size*size);
  }
  
  /**
   * @return the appropriate icon size view should use to display
   */
  public int getIconSize(){
    if (size <= 10)
      return DotButton.NORMAL_SIZE;
    else if(size <= 25)
      return DotButton.MEDIUM_SIZE;
    return DotButton.SMALL_SIZE;
  }
  /**
   * returns the stack of history
   */
  public static GameModel getHistory(){
    return (GameModel)history.pop();
  }
  
  public void save(){
    history.push(this);
  }
  public void save(GameModel m){
    history.push(m);
  }
  
  public boolean hasHistory(){
    return !history.isEmpty();
  }
  
  public void clearHistory(){
    history = new LinkedStack<GameModel>();
  }
  
  /**
   * returns all neighboring dots at coordinates
   * 
   * @param x the x coordinate to search
   * @param y the y coordinate to search
   * 
   * @return returns a stack of DotInfos with all the dots neighboring (x,y)
   */
  public LinkedStack<DotInfo> getNeighbors(int x, int y){
    LinkedStack<DotInfo> t = new LinkedStack<DotInfo>();
    if(x > 0) t.push(get(x-1,y));
    else if (isTorus) t.push(get(size-1,y));
    
    if(x<size-1)  t.push(get(x+1,y));
    else if (isTorus) t.push(get(0,y));
    
    if(y > 0) t.push(get(x,y-1));
    else if (isTorus) t.push(get(x,size-1));
    
    if(y<size-1)  t.push(get(x,y+1));
    else if (isTorus) t.push(get(x,0));
    
    //insert diagonal scanning here...
    
    return t;
  }
  public LinkedStack<DotInfo> getNeighbors(DotInfo d){
    return getNeighbors(d.getX(),d.getY());
  }
  
  /**
   * returns a copy of this model
   */
  @Override
  public Object clone(){
    //history.push(this);
    //System.out.println(this);
    try {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(baos);
      oos.writeObject(this);
      
      ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
      ObjectInputStream ois = new ObjectInputStream(bais);
      return ois.readObject();
    } catch (IOException e) {
      System.out.println(e);
      return null;
    } catch (ClassNotFoundException e) {
      System.out.println(e);
      return null;
    }
  }
  /**
   * Builds a String representation of the model
   *
   * @return String representation of the model
   */
  public String toString(){
    String s = "";
    int i = 0;
    while(i < size*size){
      s += dots[i].getColor();
      if ((i+1)%size == 0)
        s += "\n";
      i++;
    }
    return s;
  }
}