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
// Question: 2

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
        boolean[] set = new boolean[range];// the calendar
        int count = 1, draw; // count starts at 2 becasue this is the minimum for a repeat (aka one person), draw: the current draw
        
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
        int start, max, runs;
        if(args.length == 3){
            try{
                start = Integer.parseInt(args[0]);
                max = Integer.parseInt(args[1]);
                runs = Integer.parseInt(args[2]);
            }catch(NumberFormatException e){// if typo just go default
                start = 100;
                max = 10000;
                runs = 1000;
            }
        }
        else {// if no input go default
            start = 100;
            max = 10000;
            runs = 1000;
        }
        ITI1121Chart chart = new ITI1121Chart("Birthday Paradox");
        for (int i = 1; i <= max/start; i ++){
            Statistics stats = runExperiments(i*start,runs);
            chart.addDataPoint(i*start,stats.average(),stats.standardDeviation());
        }

        chart.addPolynome(0.525);
        chart.addPolynome(0.492);
        chart.addPolynome(0.555);
        chart.render();
    }
    
}