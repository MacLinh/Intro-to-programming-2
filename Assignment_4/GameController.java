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
   * used to hold redoable actions
   */
  private LinkedStack<GameModel> previousMoves;
  
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
    previousMoves = new LinkedStack<GameModel>();
    newGame();
  }
  
  /**
   * resets the game but may still be undone
   */
  public void reset(){
    saveModel();
    model.reset();
    view.update(model);
    view.setRedoable(false);
    //System.out.println(GameModel.history.peek().getNumberOfSteps());
  }
  
  /**
   * resets without saving
   */
  public void newGame(){
    model.reset();
    view.update(model);
    view.setUndoable(false);
  }
  
  /**
   * Callback used when the user clicks a button (reset or quit)
   *
   * @param e
   *            the ActionEvent
   */
  
  public void actionPerformed(ActionEvent e) {
    //System.out.println(GameModel.history.peek().getNumberOfSteps());
    if(model.getNumberOfSteps() == -1){ // new game
      Object o = e.getSource();
      int command = Integer.parseInt(e.getActionCommand());
      if (o instanceof DotButton){
        DotButton zero = (DotButton) o;
        floodZero(zero.getColor(),zero.getColumn(),zero.getRow());
        return;
      }
      else if (command == 6)
        reset();
      else if (command == 7)
        view.showQuitDialogue();
      else if (command == 8)
        undo();
      else if (command == 9)
        redo();
      else if (command == 10)
        view.displayOptions(model);
      else
        throw new IllegalArgumentException("button does not exist");
      return;
    }
    
    int command = Integer.parseInt(e.getActionCommand());
    if (command < 6)
      selectColor(command);
    else if (command == 6)
      reset();
    else if (command == 7)
      view.showQuitDialogue();
    else if (command == 8)
      undo();
    else if (command == 9)
      redo();
    else if (command == 10)
      System.out.println(view.displayOptions(model));
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
    System.out.println("select color");
    if(color == model.getCurrentSelectedColor()) // do nothing if misclick
      return;
    
    saveModel();
      
    view.setRedoable(false);
    previousMoves = new LinkedStack<GameModel>();
    model.setCurrentSelectedColor(color);  
    flood(color);
    model.step();
    view.update(model);
    
    if(model.isFinished()) {
      model.clearHistory();
      view.displayWin(model.getNumberOfSteps());
    }
    
  }
  
  
  /**
   * undoes a move
   */
  private void undo(){
    GameModel tmp = GameModel.getHistory();
    if(!model.hasHistory())
      view.setUndoable(false);
    previousMoves.push(model);
    view.setRedoable(true);
    model = tmp;
    view.update(model);
    
  }
  
  /**
   * redoes a move
   * precondition: previousMoves must not be empty
   */
  private void redo(){
    saveModel();
    GameModel tmp = previousMoves.pop();
    if(previousMoves.isEmpty())
      view.setRedoable(false);
    model = tmp;
    view.update(model);
  }
  
  
  /**
   * saves a copy of the current model
   */
  private void saveModel(){
    model.save();
    model = (GameModel)model.clone();
    view.setUndoable(true);
  }
  
  
  /**
   * captures the first dot to start the game
   * 
   * @param color the color to be flooded
   */
  private void floodZero(int color, int x, int y){
    saveModel();
    
    DotInfo dotZero = model.get(x,y);
    dotZero.setCaptured(true); // captures the top corner to start the game
    model.progress();
    model.step();
    model.setCurrentSelectedColor(dotZero.getColor());
    flood(dotZero.getColor());
    view.update(model);
  }
  
  /**
   * floods the board based on the selected color
   * 
   * @param color the color to be flooded
   */
  private void flood(int color){
    LinkedStack<DotInfo> stack = new LinkedStack<DotInfo>(),n = new LinkedStack<DotInfo>();
    
    for(DotInfo dot : model.getDots()){
      if(dot.isCaptured()){
        dot.setColor(color);
        stack.push(dot);
      }
    }
    
    do{
      DotInfo d1 = stack.pop();
      n = model.getNeighbors(d1);
      while(!n.isEmpty()){
        DotInfo d2 = n.pop();
        if(!d2.isCaptured() && d1.equals(d2)){
          d2.setCaptured(true);
          model.progress();
          stack.push(d2);
        }
      }
    }while(!stack.isEmpty());
    
  }
  
}