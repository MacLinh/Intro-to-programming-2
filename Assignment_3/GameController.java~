// Authors: Aleeza Ladhani, Mac Linh Pham
// Student numbers: 8195730, 8703691
// Course: ITI 1121-C
// Assignment: 2

import java.awt.event.*;
/**
 * The class <b>GameController</b> is the controller of the game. It has a method
 * <b>selectColor</b> which is called by the view when the player selects the next
 * color. It then computesthe next step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */


public class GameController implements ActionListener {
  
  /**
   * the model
   */
  private GameModel model;
  
  /**
   * the view
   */
  private GameView view;
  
  
  /**
   * Constructor used for initializing the controller. It creates the game's view 
   * and the game's model instances
   * 
   * @param size
   *            the size of the board on which the game will be played
   */
  public GameController(int size) {
    model = new GameModel(size);
    view = new GameView(model,this);
    reset();
  }
  
  /**
   * resets the game
   */
  public void reset(){
    model.reset();
    DotInfo dotZero = model.get(0,0);
    dotZero.setCaptured(true); // captures the top corner to start the game
    model.setCurrentSelectedColor(dotZero.getColor());
    flood(dotZero.getColor());
    view.update();
  }
  
  /**
   * Callback used when the user clicks a button (reset or quit)
   *
   * @param e
   *            the ActionEvent
   */
  
  public void actionPerformed(ActionEvent e) {
    int command = Integer.parseInt(e.getActionCommand());
    if (command < 6)
        selectColor(command);
    else if (command < 7)
        reset();
    else if (command < 8)
      view.showQuitDialogue();
    else
        throw new IllegalArgumentException("button does not exist");
        
  }
  
  /**
   * <b>selectColor</b> is the method called when the user selects a new color.
   * If that color is not the currently selected one, then it applies the logic
   * of the game to capture possible locations. It then checks if the game
   * is finished, and if so, congratulates the player, showing the number of
   * moves, and gives two options: start a new game, or exit
   * @param color
   *            the newly selected color
   */
  public void selectColor(int color){
    if(color == model.getCurrentSelectedColor()) // do nothing if misclick
      return;
    
    model.setCurrentSelectedColor(color);  
    flood(color);
    model.step();
    view.update();
    if(model.isFinished())
      view.displayWin();
    
  }
  
  /**
   * floods the board based on the selected color
   * 
   * @param color the color to be flooded
   */
  private void flood(int color){
    
    //an implementation of a stack
    ArrayStack stack = new ArrayStack();
    
    for(DotInfo dot : model.getDots()){
      if(dot.isCaptured()){
        dot.setColor(color);
        stack.push(dot);
      }
    }
    
    do{
      DotInfo d = stack.pop(), n;
      int x = d.getX(), y = d.getY(), size = model.getSize();
      
      /*      [y-1]
       * [x-1][ d ][x+1]
       *      [y+1]
       */
      
      if(x > 0){ // can't check a dot not on the grid
        n = model.get(x-1,y); // dot to the left
        if((!n.isCaptured()) && n.equals(d)){
          n.setCaptured(true);
          stack.push(n);
        }
      }
      
      if(x < size-1){ 
        n = model.get(x+1,y); // dot to the right
        if((!n.isCaptured()) && n.equals(d)){
          n.setCaptured(true);
          stack.push(n);
        }
      }
      
      if(y > 0){ 
        n = model.get(x,y-1); // dot above
        if((!n.isCaptured()) && n.equals(d)){
          n.setCaptured(true);
          stack.push(n);
        }
      }
      
      if(y < size-1){ 
        n = model.get(x,y+1); // dot below
        if((!n.isCaptured()) && n.equals(d)){
          n.setCaptured(true);
          stack.push(n);
        }
      }
    }while(!stack.isEmpty());
    
  }
  
}