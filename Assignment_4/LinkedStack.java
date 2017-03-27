// Authors: Aleeza Ladhani, Mac Linh Pham
// Student numbers: 8195730, 8703691
// Course: ITI 1121-C
// Assignment: 4

public class LinkedStack<E> implements java.io.Serializable {
  /**
   * DW about it. the answer to every question in the universe
   */
  public static final long serialVersionUID = 42L;
  
  /**
   * the top of the stack
   */
  private Elem<E> top = null;
  
  public boolean isEmpty(){
    return top == null;
  }
  
  
  public E peek(){
    if(isEmpty())
      throw new EmptyStackException();
    return top.elem;
  }
  
  
  public E pop(){
    if(isEmpty())
      throw new EmptyStackException();
    E tmp = top.elem;
    top = top.next;
    return tmp;
  }
  
  
  public void push(E stuff){
    if(stuff == null)
      throw new IllegalArgumentException("Cannot push null pointer to stack");
    top = new Elem<E>(stuff,top);
  }
  
  private static class Elem<T> implements java.io.Serializable{
    T elem;
    Elem<T> next;
    
    /**
     * DW about it. the answer to every question in the universe
     */
    static final long serialVersionUID = 42L;
    
    private Elem(T elem,Elem<T> next){
      this.elem = elem;
      this.next = next;
    }
  }
  private static class EmptyStackException extends RuntimeException {
    
    /**
     * DW about it. the answer to every question in the universe
     */
    public static final long serialVersionUID = 42L;
    
    EmptyStackException(){
      this("Stack is Empty");
    }
    EmptyStackException(String s){
      super(s);
    }
  }
  public static void main(String [] args){
    
  }
}