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
        
        stepsLabel = new JLabel("Number of steps: 0");
        stepsLabel.setPreferredSize(new Dimension(150,50));
        
        reset = new JButton("Reset");
        reset.setPreferredSize(new Dimension(69,34));
        reset.setActionCommand("6");
        reset.addActionListener(controller);
        
        quit = new JButton("Quit");
        quit.setPreferredSize(new Dimension(69,34));
        quit.setActionCommand("7");
        quit.addActionListener(controller);
        
        //holds color selectors, quit and reset buttons
        actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,4,4));
        for(int i = 0; i < GameModel.NUMBER_OF_COLORS; i++){
            DotButton dot = new DotButton(i,50);
            dot.addActionListener(controller);
            actionPanel.add(dot);
        }
        
        actionPanel.setPreferredSize(new Dimension(335,115));
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
        setSize(width+20,height+150);// have to make slightly bigger cus the size includes the borders
        setResizable(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);// USE THE QUIT BUTTON TO CLOSE
        
        add(grid);
        add(actionPanel);
        
        try{
            Thread.sleep(99); // gives it some time to load
        }catch (Exception e){ //InterruptedException
            //shouldn't happen
        }
        
        setVisible(true);
    }
    
    
    /**
     * update the status of the board's DotButton instances based on the current game model
     */
    public void update(){
        for (int i = 0; i < dots.length; i++){
            dots[i].setColor(model.getColor(i));
        }
        stepsLabel.setText("Number of Steps: " + model.getNumberOfSteps());
    }
    
    /**
     * creates a pop up dialogue when the user wins the game
     */
    public void displayWin(){
        try{
            Thread.sleep(100);
        }catch(InterruptedException e){
        }
        Object[] options = {"play again",
            "exit"};
        int n = JOptionPane.showOptionDialog(this,
                                             "You won in "+model.getNumberOfSteps()+" steps!\nWould you like to go again?",
                                             "You Win!",
                                             JOptionPane.YES_NO_OPTION,
                                             JOptionPane.QUESTION_MESSAGE,
                                             DotButton.icons[2][model.getCurrentSelectedColor()],     
                                             options,
                                             options[0]);
        if (n == 0)
            controller.reset();
        else
            System.exit(0);
    }
}