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
    model.get(0,0).setCaptured(true); // captures the top corner to start the game
  }
  
  /**
   * resets the game
   */
  public void reset(){
    model.reset();
    view.update();
  }
  
  /**
   * Callback used when the user clicks a button (reset or quit)
   *
   * @param e
   *            the ActionEvent
   */
  
  public void actionPerformed(ActionEvent e) {
    Object b = e.getSource(); // 
    //System.out.println(e.getActionCommand());
    if (b instanceof DotButton){ //select a color
      int color = Integer.parseInt(e.getActionCommand());
      selectColor(color);
    }
    else { // if its a normal button then reset or quit
      String s = e.getActionCommand();
      
      if(s.equals("Reset"))
        reset();
      else if(s.equals("Quit"))
        System.exit(0);
    }
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
    flood(color);
    model.step();
    view.update();
    
  }
  
  /**
   * floods the board based on the selected color
   * 
   * @param color the color to be flooded
   */
  private void flood(int color){
    
    //an implementation of a stack
    Stack<DotInfo> stack = new Stack<DotInfo>(){ // create an empty stack
      DotInfo[] dots = new DotInfo[model.getSize()*model.getSize()];//max size of the stack is all the dots
      int index = -1; // the top of the stack
        
      @Override
      public boolean isEmpty(){
        return index == -1;
      }
      
      @Override
      public DotInfo peek(){
        if(!isEmpty()) // not really necessary for this assignment but why not?
          return dots[index];
        return null;
      }
      
      @Override
      public DotInfo pop(){
        if(!isEmpty())
          return dots[index--];
        return null;
      }
      
      @Override
      public void push(DotInfo dot){
         dots[++index] = dot;
      }
    };
    
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
      
      if(x > 0){ //can't check a dot not on the grid
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