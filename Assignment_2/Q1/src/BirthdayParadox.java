/**
 * The class  <b>BirthdayParadox</b> is used to
 * simulated the so-called Birthday paradox, and uses
 * the class <b>Statistics</b> to store the results of
 * the experiments.
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */

// Authors: Aleeza Ladhani, Mac Linh Pham
// Student numbers: 8195730, 8703691
// Course: ITI 1121-C
// Assignment: 2
// Question: 1

public class BirthdayParadox {
    
    /** 
     * Random generator 
     */
    private static java.util.Random generator = new java.util.Random();
    

    /** 
     * Runs the series of experiments, and stores the result into
     * a Statistics object
     * 
     * @param range the size of the set from which random numbers are drawn
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
     * 
     * simulates what a real life person would do if faced with the birthday paradox in real life
     * eg take 365 days :
     * 
     * the tester would ask people for their birthday and mark it on a calendar. If someone says their
     * birthday is date already marked on the calendar then thats the number of people needed to get a repeat
     */
    
    private static int oneRun(int range){
        boolean[] set = new boolean[range];// the calendar
        int count = 1, draw; // count starts at 2 because this is the minimum for a repeat (aka one person), draw: the current draw
        
        draw = generator.nextInt(range);// random number in range [0,range) (Jan 1 = 0)
        set[draw] = true; // flags the value as already drawn (false index is one that hasn't been drawn)
        
        while (true){
            draw = generator.nextInt(range);
            
            if(set[draw]) // if index was flagged its a repeat
                return count;
            set[draw] = true; // flags the index
            count++;
        }
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
        StudentInfo.display();
        //long startTime = System.currentTimeMillis();
        int range, runs;
        if(args.length == 2){
            try{
                range = Integer.parseInt(args[0]);
                runs = Integer.parseInt(args[1]);
            }catch(NumberFormatException e){// if typo just go default
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
        //System.out.println("Total Runtime = "+ (System.currentTimeMillis()-startTime));
    }
}