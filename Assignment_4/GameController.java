// Authors: Aleeza Ladhani, Mac Linh Pham
// Student numbers: 8195730, 8703691
// Course: ITI 1121-C
// Assignment: 2

import java.awt.event.*;
import java.io.*;

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
        try {
            FileInputStream fileIn = new FileInputStream("savedGame.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            model = (GameModel) in.readObject();
            in.close();
            fileIn.close();
        }catch(Exception e) {
            System.out.println("No saved model found. New Game started");
            model = new GameModel(size);
            view = new GameView(model,this);
            newGame();
        }
        if (model.getSize() != size) // in case the user puts in a new size then what the saved game was
            model = new GameModel(size);
        if (view == null)
            view = new GameView(model,this);
        view.update(model);
    }
    
    /**
     * resets the game but may still be undone
     */
    public void reset(){
      model.clearFuture();
      saveModel();
      newGame();
    }
    
    /**
     * resets without saving
     */
    public void newGame(){
        model.reset();
        view.update(model);
    }
    
    /**
     * Callback used when the user clicks a button (reset or quit)
     *
     * @param e
     *            the ActionEvent
     */
    
    public void actionPerformed(ActionEvent e) {
        int command = Integer.parseInt(e.getActionCommand());
        
        if(model.getNumberOfSteps() == -1){ // new game
            Object o = e.getSource();
            if (o instanceof DotButton){
                DotButton zero = (DotButton) o;
                floodZero(zero.getColor(),zero.getColumn(),zero.getRow());
                return;
            }
        }
        
        if (command < 6)
            selectColor(command);
        else if (command == 6)
            reset();
        else if (command == 7){
            if(view.showQuitDialogue())
                saveAndExit();    
        }
        else if (command == 8)
            undo();
        else if (command == 9)
            redo();
        else if (command == 10) {
            int n = view.displayOptions(model);
            if (n != 0) {
                saveModel();
                model.clearFuture();
                model.setOptions(n);
                view.update(model);
            }
        }
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
        
        saveModel();
        
        model.clearFuture();
        model.setCurrentSelectedColor(color);  
        flood(color);
        model.step();
        view.update(model);
        
        if(model.isFinished()) {
            model.clearHistory();
            if(view.displayWin(model.getNumberOfSteps())) {
               File old = new File("savedGame.ser");
               old.delete();
               System.exit(0); 
            }
            else
                newGame();
        }
        
    }
    
    
    /**
     * undoes a move
     */
    private void undo(){
      GameModel tmp = model;
      model = model.getHistory().pop();
      model.getFuture().push(tmp);
      view.update(model);
    }
    
    /**
     * redoes a move
     */
    private void redo(){
        saveModel();
        model = model.getFuture().pop();
        view.update(model);
    }
    
    
    /**
     * saves a copy of the current model
     */
    private void saveModel(){
        model.getHistory().push((GameModel)model.clone());
    }
    
    private void saveAndExit(){
        System.out.println("exiting");
        try {
            FileOutputStream fileOut =
                new FileOutputStream("savedGame.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(model);
            out.close();
            fileOut.close();
        }catch(IOException e) {
            System.out.println("Cannot save game");
        }
      
        System.exit(0);
    }
    /**
     * captures the first dot to start the game
     * 
     * @param color the color to be flooded
     */
    private void floodZero(int color, int x, int y){
        saveModel();
        
        model.clearFuture();
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
        LinkedStack<DotInfo> 
          stack = new LinkedStack<DotInfo>(), // the stack of captured dots
          n = new LinkedStack<DotInfo>(); // the stack of neighboring dots
        
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