import java.awt.*;
import javax.swing.*;


/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out the actual game and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView2 extends JFrame {
  
  /**
   * this panel will hold the size x size grid of dots
   */
  private JPanel grid;
  
  /**
   * this panel will hold the buttons for the game
   */
  private JPanel actionPanel;
  
  /**
   * all the dots on the grid
   */
  private DotButton[] dots;
  
  /**
   * displays the number of steps taken
   */
  private JLabel stepsLabel;
 
  /**
   * the reset and quit buttons
   */
  private JButton reset, quit;
  
   /**
   * the size of the buttons to be displayed
   */
  private int iconSize;
  
  /**
   * the current state of the game
   */
  private GameModel model;
  
  /**
   * the controller
   */
  private GameController controller;
  
  /**
   * Constructor used for initializing the Frame
   * 
   * @param model
   *            the model of the game (already initialized)
   * @param gameController
   *            the controller
   */
  
  public GameView2(GameModel model, GameController gameController) {
    super();
    this.model = model;
    controller = gameController;
    iconSize = 50;//model.getIconSize();
    dots = new DotButton[model.getSize()*model.getSize()];
    init();
  }
  
  /**
   * initializes all the JComponents
   */
  private void init(){
    int width, height;
    System.out.println(iconSize);
    width = height = iconSize*model.getSize();
    
    stepsLabel = new JLabel("Number of steps: 0");
    stepsLabel.setPreferredSize(new Dimension(150,50));
    
    reset = new JButton("Reset");
    reset.setPreferredSize(new Dimension(70,35));
    reset.addActionListener(controller);
    
    quit = new JButton("Quit");
    quit.setPreferredSize(new Dimension(65,35));
    quit.addActionListener(controller);
    
    actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
    for(int i = 0; i < GameModel.NUMBER_OF_COLORS; i++){
      DotButton dot = new DotButton(i,50);
      dot.addActionListener(controller);
      actionPanel.add(dot);
    }
    //actionPanel.setBackground(Color.black);
    actionPanel.setPreferredSize(new Dimension(335,115));
    actionPanel.add(stepsLabel);
    actionPanel.add(reset);
    actionPanel.add(quit);
    
    //very important to load the grid last so that the images loaded is for the grid
    
    grid = new JPanel(new FlowLayout(FlowLayout.LEADING,0,0));
    grid.setPreferredSize(new Dimension(width,height));
    update();
    for(DotButton dot : dots){
      grid.add(dot);
    }
    
    setLayout(new FlowLayout(FlowLayout.CENTER));
    setSize(width+20,height+150);// have to make slightly bigger cus the size includes the borders
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
    add(grid);
    add(actionPanel);
    pack();
  }
  
  /**
   * update the status of the board's DotButton instances based on the current game model
   */
  public void update(){
    /*for (int i = 0; i < dots.length; i++){
      if (dots[i] == null)
        dots[i] = new DotButton(model.getColor(i),iconSize);
      else
      dots[i].setColor(model.getColor(i));
      }*/
      int n = 0;
      for(int i = 0; i < model.getSize();i++){
          for(int j = 0; j < model.getSize();j++){
              if (dots[n] == null)
                  dots[n] = new DotButton(model.getColor(i,j),iconSize);
              else
                  dots[n].setColor(model.getColor(i,j));
              n++;
          }
      }
      stepsLabel.setText("Number of Steps: " + model.getNumberOfSteps());
  }
  
  public void displayWin(){
    Object[] options = {"play again",
      "exit"};
    int n = JOptionPane.showOptionDialog(this,
                                         "You won in "+model.getNumberOfSteps()+" steps!\nwWould you like to go again?",
                                         "won",
                                         JOptionPane.YES_NO_OPTION,
                                         JOptionPane.QUESTION_MESSAGE,
                                         null,     
                                         options,
                                         options[0]);
    if (n == 0)
      controller.reset();
    else
      System.exit(0);
  }
}