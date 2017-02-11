/**
 * The class  <b>BirthdayParadox</b> is used to
 * simulated the so-called Birthday paradox, and uses
 * the class <b>Statistics</b> to store the results of
 * the experiments.
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */

public class BirthdayParadox {
    
    /** 
     * Random generator 
     */
    private static java.util.Random generator = new java.util.Random();
    
    
    /** 
     * Runs the series of experiments, and stores the result into
     * a Statistics object
     * 
     * @param range the size of the set from which random number are drawn
     * @param numberOfRuns the number of experiments to run
     *
     * @return a reference to a Statistics instance that holds the result
     * of the experiment
     */
    public static Statistics runExperiments(int range, int numberOfRuns){
        Statistics stats = new Statistics(numberOfRuns);
        for (int i = 0; i < numberOfRuns;i++){
            stats.updateStatistics(oneRun(range));
        }
        return stats;
        
    }
    
    /** 
     * Runs a single experiment.
     * The parameter range defines the size of the set from which
     * the experiment is drawn
     * 
     * @param range the size of the set from which random number are drawn
     *
     * @return the number of random draw in the set that the method 
     * used before drawing the same element for the second time
     */
    
    private static int oneRun(int range){
        /*int[] drawn = new int[range+1];//statistically there is 100% probability at one more than range so this is max amount
        int i = 0, draw;
        //this is not redundant, there needs to be at least 2 elemnts for a repeat so this will save one cycle of contains()
        //saves one whole cycle for every experiment run!
        draw = generator.nextInt(range)+1;// generates a random number between 1 and range inclusive [1,range]
        drawn[i++] = draw;//adds to list of already drawn values
        do{
            draw = generator.nextInt(range)+1;
            drawn[i++] = draw; 
        }while(!contains(drawn,i-1,draw));//keep drawing until draw a value already drawn
        return i; */
        int[] drawn = new int[range+1];
        int count = 2, draw;
        boolean active = true;
        
        draw = generator.nextInt(range)+1;
        drawn[draw-1] = draw;
        
        while (active){
            draw = generator.nextInt(range)+1;
            
            if(drawn[draw-1] == draw)
                return count;
            else{
                drawn[draw-1] = draw;
                count++;
            }
        }
        return -1;
    }
    
    /**
     * I will implement my own search algorithm because im not sure if allowed to use libraries
     * returns true if and only if the value is contained in the array
     * 
     * linear search will be fastest since sorting then using binary search is slower
     * extra condition is I will assume the array should not be fully initialized because it is very rare that we must
     * get to 366 days for a common birthday...
     * 
     * @param arr the array to be scanned
     * @param maxIndex the max index that this will scan to
     * @param elem the value searching for
     */
    
    /*
    private static boolean contains(int[] arr, int maxIndex, int elem){
        for (int i = 0; i < maxIndex; i++){
            if (arr[i] == elem)
                return true;
        }
        return false;
    }*/
    
    /** 
     * Main method. The default size of the set is 365, and
     * the experiment is run 50 times. Both numbers can be reset
     * from the command line.
     * This method runs the experiments and prints the
     * resulting Statistics
     * 
     * @param args if not empty, contains the runtime values for
     * the size of the set and the number of runs
     */
    public static void main(String[] args) {
        int range, runs;
        if(args.length == 2){
            try{
                range = Integer.parseInt(args[0]);
                runs = Integer.parseInt(args[1]);
            }catch(NumberFormatException e){//if typo just go default
                range = 365;
                runs = 50; 
            }
        }
        else {// if no input go default
            range = 365;
            runs = 50; 
        }
        System.out.println("We have run "+ runs + " experiments");
        System.out.println(runExperiments(range,runs));
    }
    
}