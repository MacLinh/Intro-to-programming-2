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
        int[] drawn = new int[range+1];//at range +1 trials the probability is 100% 
        int count = 2, draw; //count starts at 2 cus minimum for a repeat, draw: the current draw
        boolean active = true;
        
        //I shifted all the indexes down one because I can't use 0 as unique number due to 0 being default value in arrays
        
        draw = generator.nextInt(range)+1;// random number in range [1,range] (Jan 1 = 1)
        drawn[draw-1] = draw; // [0] = 1, [1] = 2, [k] = k+1 ect... not drawn = 0 
        
        while (active){
            draw = generator.nextInt(range)+1;
            
            if(drawn[draw-1] == draw) // basically if not 0 its a repeated draw
                return count;
            drawn[draw-1] = draw; // if the number is new add it to the list
            count++;
        }
        return -1; // shouldnt happen 
    }
    
    
    
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