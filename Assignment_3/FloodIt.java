

/**
 * The class <b>FloodIt</b> launches the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class FloodIt {


   /**
     * <b>main</b> of the application. Creates the instance of  GameController 
     * and starts the game. If a game size (<12) is passed as parameter, it is 
     * used as the board size. Otherwise, a default value is passed
     * 
     * @param args
     *            command line parameters
     */
     public static void main(String[] args) {
         int size = 12;
         if (args.length == 1){
             try{
                 size = Integer.parseInt(args[0]);
             }catch(NumberFormatException e){
                 System.err.println("please input a number, default values used");
             }
             if(10 > size || size > 30){
                 System.err.println("default values used");
                 size = 12;
             } 
         }
       new GameController(size);
   }


}