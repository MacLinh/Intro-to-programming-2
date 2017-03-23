public class LinkedStack<E>  {
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
  
  private static class Elem<T>{
    T elem;
    Elem<T> next;
    private Elem(T elem,Elem<T> next){
      this.elem = elem;
      this.next = next;
    }
  }
  private static class EmptyStackException extends RuntimeException{
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