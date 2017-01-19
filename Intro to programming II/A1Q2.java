public class A1Q2{
  private static boolean hasTwoLengthRun(int [] elems){
    for (int i = 0; i < elems.length-1; i++){
      if (elems[i] == elems[i+1])
        return true;
    }
    return false;
  }
  public static void main(String [] args){
    int[] elems = A1Q1.convert(args);
    System.out.println(hasTwoLengthRun(elems));
  }
}