// Authors: Aleeza Ladhani, Mac Linh Pham
// Student numbers: 8195730, 8703691
// Course: ITI 1121-C
// Assignment: 2

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
     * this panel will hold the options buttons on the top for the game
     */
    private JPanel controlPanel;
    
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
     * the reset, quit, undo, redo, and options buttons
     */
    private JButton reset, quit, undo, redo, options;
    
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
    public GameView(GameModel model, GameController gameController) {
        super("Flood It! The ITI1121 Version");
        this.model = model;
        controller = gameController;
        iconSize = model.getIconSize();
        dots = new DotButton[model.getSize()*model.getSize()];
        init();
    }
    
    
    /**
     * initializes all the Components
     */
    private void init(){
        int width, height;
        
        width = height = iconSize*model.getSize();
        
        stepsLabel = new JLabel("Select Initial Dot");
        stepsLabel.setPreferredSize(new Dimension(150,50));
        
        reset = new JButton("Reset");
        reset.setPreferredSize(new Dimension(69,34));
        reset.setActionCommand("6");
        reset.addActionListener(controller);
        
        quit = new JButton("Quit");
        quit.setPreferredSize(new Dimension(69,34));
        quit.setActionCommand("7");
        quit.addActionListener(controller);
        
        undo = new JButton("undo");
        undo.setPreferredSize(new Dimension(69,34));
        undo.setActionCommand("8");
        undo.setEnabled(false);
        undo.addActionListener(controller);
        
        redo = new JButton("redo");
        redo.setPreferredSize(new Dimension(69,34));
        redo.setActionCommand("9");
        redo.setEnabled(false);
        redo.addActionListener(controller);
        
        options = new JButton("options");
        options.setPreferredSize(new Dimension(69,34));
        options.setActionCommand("10");
        options.addActionListener(controller);
        
        controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,4,4));
        controlPanel.add(undo);
        controlPanel.add(redo);
        controlPanel.add(options);
        
        //holds color selectors, quit and reset buttons
        actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,4,4));
        //for(int i = 0; i < GameModel.NUMBER_OF_COLORS; i++){
        //    DotButton dot = new DotButton(i,50);
        //    dot.addActionListener(controller);
        //    actionPanel.add(dot);
        //}
        
        actionPanel.setPreferredSize(new Dimension(335,80));
        actionPanel.add(stepsLabel);
        actionPanel.add(reset);
        actionPanel.add(quit);
        
        //holds the grid of dots
        grid = new JPanel(new GridLayout(model.getSize(),model.getSize()));
        grid.setPreferredSize(new Dimension(width,height));
        for (int i = 0; i < dots.length; i++){
            dots[i] = new DotButton(model.getColor(i),iconSize);
            dots[i].addActionListener(controller);
            grid.add(dots[i]);
        }
        
        setLayout(new FlowLayout());
        setSize(width+20,height+180);// have to make slightly bigger cus the size includes the borders
        setResizable(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);// USE THE QUIT BUTTON TO CLOSE
        
        add(controlPanel);
        add(grid);
        add(actionPanel);
        
        try{
            Thread.sleep(99); // gives it some time to load
        }catch (Exception e){ //InterruptedException
            //shouldn't happen
        }
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    
    /**
     * update the status of the board's DotButton instances based on the current game model
     */
    public void update(GameModel model){
        for (int i = 0; i < dots.length; i++){
            dots[i].setColor(model.getColor(i));
        }
        if(model.getNumberOfSteps() != -1)
          stepsLabel.setText("Number of Steps: " + model.getNumberOfSteps());
        else 
          stepsLabel.setText("Select Initial Dot");
    }
    
    /**
     * enables or disables the redo button
     */
    public void setUndoable(boolean b){
      undo.setEnabled(b);
    }
    
    /**
     * enables or disables the redo button
     */
    public void setRedoable(boolean b){
      redo.setEnabled(b);
    }
    
    /**
     * creates a popup to enable options
     * 
     * @ return returns 1 if torus is changed, 2 if diagonal and 3 if both, and 0 if unchanged
     */
    public int displayOptions(GameModel model){
      JDialog d = new JDialog(this,"Options",true);
      
      JLabel tLabel = new JLabel("Play on Plane or Torus?");
      JRadioButton plane = new JRadioButton("Plane",!model.isTorus());
      JRadioButton torus = new JRadioButton("Torus",model.isTorus());
      
      JLabel dLabel = new JLabel("    Diagonal moves?");
      JRadioButton ortho = new JRadioButton("Orthogonal",!model.isDiagonal());
      JRadioButton diagonal = new JRadioButton("Diagonal",model.isDiagonal());
      
      ButtonGroup tGroup = new ButtonGroup();
      tGroup.add(plane);
      tGroup.add(torus);
      
      ButtonGroup dGroup = new ButtonGroup();
      dGroup.add(ortho);
      dGroup.add(diagonal);
      
      d.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
      d.add(tLabel);
      d.add(plane);
      d.add(torus);
      d.add(dLabel);
      d.add(ortho);
      d.add(diagonal);
      
      Point p = getLocation();
      Point mid = new Point((int)p.getX(),(int)p.getY()+100);
      d.setLocation(mid);
      //d.setLocationRelativeTo(this);
      d.pack();
      d.setVisible(true);
      
      int n = 0;
      if (model.isDiagonal() != diagonal.isSelected()) 
          n += 2;
      if(model.isTorus() != torus.isSelected())
          n += 1;
      return n;
    }
    
    
    /**
     * creates a pop up dialogue when the user wins the game
     */
    public void displayWin(int steps){
        try{
            Thread.sleep(100);
        }catch(InterruptedException e){
        }
        Object[] options = {"play again",
            "exit"};
        int n = JOptionPane.showOptionDialog(this,
                                             "You won in "+steps+" steps!\nWould you like to go again?",
                                             "You Win!",
                                             JOptionPane.YES_NO_OPTION,
                                             JOptionPane.QUESTION_MESSAGE,
                                             DotButton.icons[2][model.getCurrentSelectedColor()],     
                                             options,
                                             options[0]);
        if (n == 0)
            controller.newGame();
        else
            System.exit(0);
    }
    
    public void showQuitDialogue(){
        Object[] options = {"Cancel",
            "Exit"};
        int n = JOptionPane.showOptionDialog(this,
                                             "Are you sure?",
                                             "Exit Game?",
                                             JOptionPane.YES_NO_OPTION,
                                             JOptionPane.QUESTION_MESSAGE,
                                             DotButton.icons[2][model.getCurrentSelectedColor()],     
                                             options,
                                             options[0]);
        if (n == 0);
        else
            System.exit(0);
    }
    
}