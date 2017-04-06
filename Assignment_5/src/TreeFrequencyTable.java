import java.util.NoSuchElementException;

/** Implements the interface <code>FrequencyTable</code> using a
 *  binary search tree.
 *
 * @author Marcel Turcotte (turcott@eecs.uottawa.ca)
 */

public class TreeFrequencyTable implements FrequencyTable {

    // Stores the elements of this binary search tree (frequency
    // table)
    
    private static class Elem {
    
        private String key;
        private long count;
    
        private Elem left;
        private Elem right;
    
        private Elem(String key) {
            this.key = key;
            this.count = 0;
            left = null;
            right = null;
        }
    }

    private Elem root = null; // A reference to the root element
    private int size = 0; // The size of the tree

    /** The size of the frequency table.
     *
     * @return the size of the frequency table
     */
    
    public int size() {
        return size;
    }
  
    /** Creates an entry in the frequency table and initializes its
     *  count to zero.
     *
     * @param key key with which the specified value is to be associated
     */
  
    public void init(String key) {
       add(key,root);
    
    }
    
    private void add(String key, Elem e){
        if(e == null) {
            e = new Elem(key);
        }
        else {
            if (key.equals(e.key)) throw new IllegalArgumentException(key + " already exists");
            if (key.compareTo(e.key) < 0) // less than
                add(key,e.left);
            else 
                add(key,e.right);  
        }
    }
  
    /** The method updates the frequency associed with the key by one.
     *
     * @param key key with which the specified value is to be associated
     */
  
    public void update(String key) {
       find(key,root).count++;
    }
    private Elem find(String key, Elem e){
        if (e == null) // not found
            throw new NoSuchElementException(key + "doesnt exist");
        if (e.key.equals(key))
            return e;
        if (key.compareTo(e.key) < 0) // less than
            return find(key,e.left);
        else 
            return find(key,e.right); 
        
    }
  
    /**
     * Looks up for key in this TreeFrequencyTable, returns associated value.
     *
     * @param key value to look for
     * @return value the value associated with this key
     * @throws NoSuchElementException if the key is not found
     */
  
    public long get(String key) {
      return find(key,root).count;
    }
  
    /** Returns the list of keys in order, according to the method compareTo of the key
     *  objects.
     *
     *  @return the list of keys in order
     */

    public LinkedList<String> keys() {

 throw new UnsupportedOperationException("IMPLEMENT THIS METHOD");

    }

    /** Returns the values in the order specified by the method compareTo of the key
     *  objects.
     *
     *  @return the values
     */

    public long[] values() {

 throw new UnsupportedOperationException("IMPLEMENT THIS METHOD");

    }

    /** Returns a String representation of the tree.
     *
     * @return a String representation of the tree.
     */

    public String toString() {
        return toString( root );
    }

    // Helper method.
  
    private String toString(Elem current) {
    
        if (current == null) {
            return "{}";
        }
    
        return "{" + toString(current.left) + "[" + current.key + "," + current.count + "]" + toString(current.right) + "}";
    }
  
}
