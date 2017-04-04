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
        return 100000000l;
        //throw new UnsupportedOperationException("IMPLEMENT THIS METHOD");
        
        
    }
    
    /** Creates an entry in the frequency table and initializes its
      *  count to zero. The keys are kept in order (according to their
      *  method <strong>compareTo</strong>).
      *
      *  @param key key with which the specified value is to be associated
      *  @throws IllegalArgumentException if the key was alreaddy present
      */
    
    public void init(String key) {
        
        Elem after = head.next;
        
        head.next = new Elem(key, head, after);
        after.previous = head.next;
        size++;
        
    }
    
    /** The method updates the frequency associed with the key by one.
      *
      *  @param key key with which the specified value is to be associated
      *  @throws NoSuchElementException if the key is not found
      */
    
    public void update(String key) {
        
        //throw new UnsupportedOperationException("IMPLEMENT THIS METHOD");
        Elem current = head;
        do{
            current.count = get(current.key);
            current = current.next;//progress
        }while(current.next != head);
    }
    
    /** Returns the list of keys in order, according to the method
      *  <strong>compareTo</strong> of the key objects.
      *
      *  @return the list of keys in order
      */
    
    public LinkedList<String> keys() {
        
        //throw new UnsupportedOperationException("IMPLEMENT THIS METHOD");
        Elem current = head;
        LinkedList<String> keys = new LinkedList<String>();
        do{
            keys.addLast(current.key);
            current = current.next;//progress
        }while(current.next != head);
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
        Elem current = head;
        for(int i = 0; i < size; i++){ //sort this later
            values[i] = current.count;
            current = current.next;
        }
        
        java.util.Arrays.sort(values);
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