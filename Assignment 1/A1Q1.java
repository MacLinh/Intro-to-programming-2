public class A1Q1{
  private static int countPositive(int[] elems){
    int count = 0;
    for (int i : elems){
      if (i > 0)
        count++;
    }
    return count;
  }
  //I made this method to convert String[] args to int[] for Q1 Q2 abd Q3
  public static int[] convert(String [] sList){
    int[] elems = new int[sList.length];
    for (int i = 0; i < sList.length; i++){
      elems[i] = Integer.parseInt(sList[i]);
    }
    return elems;
  }
  public static void main(String [] args){
    int[] elems = convert(args);
    System.out.println(countPositive(elems));
  }
}