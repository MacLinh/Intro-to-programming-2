//import java.util.NoSuchElementException;
import java.util.*;

/** Implements the interface <code>FrequencyTable</code> using a
 *  binary search tree.
 *
 * @author Marcel Turcotte (turcott@eecs.uottawa.ca)
 */

public class TreeFrequencyTable implements FrequencyTable {

    // Stores the elements of this binary search tree (frequency
    // table)
    
    private static class Elem implements Comparable{
    
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
        public int compareTo(Object o){
            if(!(o instanceof Elem))
                   return -1;
            Elem e = (Elem) o;
            return key.compareTo(e.key);
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
   //private int  threshold = 1, n = 1;
    private ArrayList<Elem> nodes = new ArrayList<Elem>(4*4*4*4*4); // cus 4^5 tuples
    public void init(String key) {
        /*System.out.println(size+ ", "+threshold);
        if (size == threshold){ 
            Elem oldTop = root;
            root = new Elem(key);
            if (oldTop.key.compareTo(key) < 0) 
                root.left = oldTop;
            else if (oldTop.key.compareTo(key) == 0)
                throw new IllegalArgumentException(key + " already exists");
            else 
                root.right = oldTop;
            threshold += ++n;
            
        } else {
            add(key,root);
        } 
        size++;*/
        if (root == null) {
            nodes.add(new Elem(key));
        }
        else
            add(key,root);
        size++;
    }
    
    /**
     * helper method to add a new element to the tree recursively
     */
    private void add(String key, Elem e){
        if(root == null) {
            root = new Elem(key); // need to fix 
        }
        else {
            if (key.equals(e.key)) throw new IllegalArgumentException(key + " already exists");
            if (key.compareTo(e.key) < 0) {// less than
                if (e.left == null)
                    e.left = new Elem(key);
                else
                    add(key,e.left);
            }
            else {
                if (e.right == null)
                    e.right = new Elem(key);
                else
                    add(key,e.right);
            }
        }
    }
  
    /** The method updates the frequency associed with the key by one.
     *
     * @param key key with which the specified value is to be associated
     */
  
    public void update(String key) {
        if(root == null) {// tree not yet created
            spawnTree();
            //find(key,root);
        }
       find(key,root).count++;
    }
    /**
     * @return a reference to the node with the specified key
     */
    private Elem find(String key, Elem e){
        
        if (e == null) // not found
            throw new NoSuchElementException(key + "doesnt exist");
        if (e.key.equals(key)) // found
            return e;
        if (key.compareTo(e.key) < 0) // less than
            return find(key,e.left);
        else 
            return find(key,e.right); 
        
    }
    public void spawnTree() {
         System.out.println(nodes.size());
         if (root == null) {
             Collections.sort(nodes);
             root = spawnTree(root,nodes);
         }
    }
    private Elem spawnTree(Elem e, List<Elem> arr){
        //Collections.sort(arr);
        int middle = (int)arr.size()/2;
        if(arr.size() == 0) // if its of size 1 then the branch is done
            return null;
        e = arr.get(middle); //gets the middle of the array
        
        e.left = spawnTree(e,arr.subList(0,middle));
        e.right = spawnTree(e,arr.subList(middle+1,arr.size()));
        
        return e;                   
    }
  
    /**
     * Looks up for key in this TreeFrequencyTable, returns associated value.
     *
     * @param key value to look for
     * @return value the value associated with this key
     * @throws NoSuchElementException if the key is not found
     */
  
    public long get(String key) {
        if (root == null) {
             Collections.sort(nodes);
             root = spawnTree(root,nodes);
         }
      return find(key,root).count;
    }
  
    /** Returns the list of keys in order, according to the method compareTo of the key
     *  objects.
     *
     *  @return the list of keys in order
     */
    private LinkedList<String> keys = new LinkedList<String>();
    public LinkedList<String> keys() {
       LinkedList<String> keys = new LinkedList<String>(); 
        for (Elem e : nodes) {
            keys.addLast(e.key);
        }
        return keys;
    }

    /** Returns the values in the order specified by the method compareTo of the key
     *  objects.
     *
     *  @return the values
     */

    public long[] values() {
        long[] values = new long[size];
        for(int i = 0; i < size; i++){
            values[i] = nodes.get(i).count;
        }
        return values;
    }

    /** Returns a String representation of the tree.
     *
     * @return a String representation of the tree.
     */

    public String toString() {
        if (root == null)
            spawnTree();
        return toString( root );
    }

    // Helper method.
  
    private String toString(Elem current) {
        String s = "";
        if (current == null) {
            return "{}";
        }
        if (current == root)
            s += "root";
        return "{" + toString(current.left) + "[" + current.key +s+ /*"," + current.count + */"]" + toString(current.right) + "}";
    }
    
    public static void main(String [] args){
        TreeFrequencyTable tree = new TreeFrequencyTable();
        int n= 300000;
        for (int i = 1; i <= n; i++){
            tree.init(""+i);
        }
        //tree.spawnTree();
        System.out.println("C".compareTo("A"));
        //System.out.print(tree.get(""+10)+",");
        for (int i = 1; i < n; i++){
        tree.get(""+i);
        }
    }
  
}
