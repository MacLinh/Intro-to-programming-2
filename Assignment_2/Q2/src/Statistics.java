/**
 * The class  <b>Statistics</b> accumulates the results of
 * the experiments. It know ahead of time how many experiments
 * will be run, and provides at the end the min, the max, the
 * average and the standard deviation for the data.
 *
 * <b> this class should not use classes such as Array, 
 * Lists etc. </b> to store the data, only prinitive types 
 * and java arrays.
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */

// Authors: Aleeza Ladhani, Mac Linh Pham
// Student numbers: 8195730, 8703691
// Course: ITI 1121-C
// Assignment: 2
// Question: 2

public class Statistics {
    
    /**
     * this array will store the values passed to this statistics object
     */
    private int[] dataSet;
    
    /**
     * numberOfRuns is the max size of statistic, index is the current index in array that is filled up
     * since index starts at 0 the size of statistics is always index | index == numberOfRuns when full
     */
    private int numberOfRuns, index = 0;
    
    /**
     * the minimum and maximum values of the current data set
     */
    private int max = -1, min = -1;
    
    /**
     * the average and standard deviation of the current data set
     */
    private double avg = -1, stdv = -1;
    
    /**
     * keeps track of if this statistics object is updated for all its values
     */
    private boolean updated;

    /** 
     * Constructor.
     * 
     * @param numberOfRuns the number of experiments that will be run
     */
    public  Statistics(int numberOfRuns){
        this.numberOfRuns = numberOfRuns;
        dataSet = new int[numberOfRuns];
        updated = false;
    }
    
    /** 
     * Updates statistics after one experiment.
     * This method cannot be called more times than the 
     * paramter that was passed in the constructor. If
     * it is, an error message should be printed and
     * no change should occur.
     * @param value the result of the new experiment
     */
    public void updateStatistics(int value){
        if (index < numberOfRuns){
            dataSet[index++] = value; // n++ evaluates first then adds 1 vs ++n which adds 1 first
            updated = false;
        }
        else
            System.err.println("Error limit reached");
    }
    
    
    /** 
     * @return the current average of the values passed
     * to the method updateStatistic
     */
    public double average(){
        double sum = 0;
        if (index == numberOfRuns){
            min = max = dataSet[0];
            for (int i = 0; i < index; i++){
                int data = dataSet[i];
                sum+= dataSet[i];
                if (data > max) // calculating min and max here to save time
                    max = data;
                else if (data < min)// else if as this is mutually exclusive to save time
                    min = data;
                else
                    continue;
            }
        }
        else {
            System.out.println("data set not full!");
            min = max = dataSet[0];
            for (int i = 0; i < index; i++){
                int data = dataSet[i];
                sum+= dataSet[i];
                if (data > max)
                    max = data;
                else if (data < min)// else if as this is mutually exclusive to save time
                    min = data;
                else
                    continue;
            }
        }
        return (double)Math.round(100d*sum/(index))/100d;
    }
    
    
    /** 
     * @return the current standard deviation of the values passed
     * to the method updateStatistic
     */
    public double standardDeviation(){
        double avg;
        if(this.avg == -1)// if average was not yet calculated
            avg = average();
        else // else just use the average to save time
            avg = this.avg;
        double sum = 0;
        for (int i = 0; i < index; i++){
            sum += Math.pow((dataSet[i] - avg),2);
        }
        return (double)Math.round(Math.sqrt(sum/(index))*100d)/100d; 
    }
    
    /**
     * calculates min, max, avg, and stdv 
     */
    private void performCalculations(){
        avg = average(); // min and max are calculated in this
        stdv = standardDeviation();
        
        updated = true;
    }
    /** 
     * @return Returns the complete statistics information:
     * current minimum, current maximim, current average and
     * current standard deviation. For the last two, only two 
     * digits decimals are shown
     */
    public String toString(){
        if (!updated)// this will save time so calculations arent performed every time the object is printed...
            performCalculations();// only calculates if not updated (ie the first time this is called for unmodified set)
        return ("\tthe minimum was: " + min // there was no ':' in the example I assume that was a mistake so I added one
                    +"\n\tthe maximum was: " + max
                    +"\n\tthe average was: " + avg
                    +"\n\tthe standard deviation was: " + stdv);
    }
}