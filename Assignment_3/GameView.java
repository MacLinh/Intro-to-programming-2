import java.awt.*;
import javax.swing.*;


/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out the actual game and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame {
  
  /**
   * this panel will hold the size x size grid of dots
   */
  JPanel grid;
  
  /**
   * this panel will hold the buttons for the game
   */
  JPanel actionPanel;
  
  /**
   * all the dots on the grid
   */
  DotButton[] dots;
  
  /**
   * the size of the buttons to be displayed
   */
  private int iconSize = 50;
  
  /**
   * the current state of the game
   */
  GameModel model;
  
  /**
   * the controller
   */
  GameController controller;
  
  /**
   * Constructor used for initializing the Frame
   * 
   * @param model
   *            the model of the game (already initialized)
   * @param gameController
   *            the controller
   */
  
  public GameView(GameModel model, GameController gameController) {
    super();
    this.model = model;
    controller = gameController;
    dots = new DotButton[model.getSize()*model.getSize()];
    init();
  }
  
  /**
   * initializes all the JComponents
   */
  private void init(){
    int width, height;
    width = height = iconSize*model.getSize();
    
    grid = new JPanel(new FlowLayout(FlowLayout.LEADING,0,0));
    grid.setPreferredSize(new Dimension(width,height));
    update();
    for(DotButton dot : dots){
      grid.add(dot);
    }
    
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
    add(grid);
    pack();
  }
  
  /**
   * update the status of the board's DotButton instances based on the current game model
   */
  public void update(){
    for (int i = 0; i < dots.length; i++){
      if (dots[i] == null)
        dots[i] = new DotButton(model.getColor(i),iconSize);
      else
        dots[i].setColor(model.getColor(i));
    }
  }
  
}