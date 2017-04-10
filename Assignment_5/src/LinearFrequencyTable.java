// Authors: Aleeza Ladhani, Mac Linh Pham
// Student numbers: 8195730, 8703691
// Course: ITI 1121-C
// Assignment: 5

import java.util.NoSuchElementException;

//import LinkedList.Elem;

/** Implements the interface <code>FrequencyTable</code> using linked
  *  elements. The linked structure is circular and uses a dummy node.
  *
  * @author Marcel Turcotte (turcott@eecs.uottawa.ca)
  */

public class LinearFrequencyTable implements FrequencyTable {
    
    // Linked elements
    
    private static class Elem {
        
        private String key;
        private long count;
        private Elem previous;
        private Elem next;
        
        private Elem(String key, Elem previous, Elem next) {
            this.key = key;
            this.count = 0;
            this.previous = previous;
            this.next = next;
        }
        
    }
    
    private Elem head;
    private int size;
    
    /** Constructs and empty <strong>FrequencyTable</strong>.
      */
    
    public LinearFrequencyTable() {
        head = new Elem(null, null, null); // dummy node
        head.previous = head; // making the dummy node circular
        head.next = head; // making the dummy node circular
        size = 0;
    }
    
    /** The size of the frequency table.
      *
      * @return the size of the frequency table
      */
    
    public int size() {
        return size;
    }
    
    /** Returns the frequency value associated with this key.
      *
      *  @param key key whose frequency value is to be returned
      *  @return the frequency associated with this key
      *  @throws NoSuchElementException if the key is not found
      */
    
    public long get(String key) {
        
        Elem e = head.next;
        while(e != head){
            if (e.key.equals(key)){
                //System.out.println("going" + key);
                return e.count;
            }
            e = e.next;
        }
        throw new NoSuchElementException(key +" does not exist ");
        
        
    }
    
    /** Creates an entry in the frequency table and initializes its
      *  count to zero. The keys are kept in order (according to their
      *  method <strong>compareTo</strong>).
      *
      *  @param key key with which the specified value is to be associated
      *  @throws IllegalArgumentException if the key was alreaddy present
      */
    
    public void init(String key) {
        size++;
        add(head.previous,key);
    }
    private void add(Elem e, String key){
        if (e == head || e.key.compareTo(key) < 0) {
            addAfter(e,key); 
            return;
        }
        add(e.previous,key);
        
        
    }
    private void addAfter(Elem before, String key) {
        Elem after = before.next;
        
        before.next = new Elem(key, before, after);
        after.previous = before.next;
        
        //size++;
        
    }
    
    /** The method updates the frequency associed with the key by one.
      *
      *  @param key key with which the specified value is to be associated
      *  @throws NoSuchElementException if the key is not found
      */
    
    public void update(String key) {
        //System.out.println(this);
        //throw new UnsupportedOperationException("IMPLEMENT THIS METHOD");
        Elem e = head.next;
        while(e != head){
            if (e.key.equals(key)){
                //System.out.println("going" + key);
                e.count++;
                return;
            }
            e = e.next;
        }
        throw new NoSuchElementException(key +" does not exist ");
        //System.out.print(key +" : " + e.key+", ");
    }
    
    /** Returns the list of keys in order, according to the method
      *  <strong>compareTo</strong> of the key objects.
      *
      *  @return the list of keys in order
      */
    
    public LinkedList<String> keys() {
        
        //throw new UnsupportedOperationException("IMPLEMENT THIS METHOD");
        Elem current = head.next;
        LinkedList<String> keys = new LinkedList<String>();
        while(current.next != head){
            keys.addLast(current.key);
            if(current.key.compareTo(current.next.key) != -1) throw new RuntimeException("u messed up its not sorted");
            current = current.next;//progress
        }
        //java.util.Collections.sort(keys);
        return keys;
    }
    
    /** Returns an array containing the frequencies of the keys in the
      *  order specified by the method <strong>compareTo</strong> of
      *  the key objects.
      *
      *  @return an array of frequency counts
      */
    
    public long[] values() {
        long[] values = new long[size]; 
        Elem current = head.next;
        for(int i = 0; i < size; i++){ //sort this later
            values[i] = current.count;
            //if(current.key.compareTo(current.next.key) >= 0) throw new RuntimeException("u messed up its not sorted" +current.key+" " +current.next.key);
            current = current.next;
            
        }
        
        return values;
    }
    
    /** Returns a <code>String</code> representations of the elements
      * of the frequency table.
      *  
      *  @return the string representation
      */
    
    public String toString() {
        
        StringBuffer str = new StringBuffer("{");
        Elem p = head.next;
        
        while (p != head) {
            str.append("{key="+p.key+", count="+p.count+"}");
            if (p.next != head) {
                str.append(",");
            }
            p = p.next;
        }
        str.append("}");
        return str.toString();
    }
    
}
