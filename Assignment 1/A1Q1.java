public class A1Q1{
  private static int countPositive(int[] elems){
    int count = 0;
    for (int i : elems){
      if (i > 0)
        count++;
    }
    return count;
  }
  //Converting String[] args to int[] for Q1 Q2 and Q3
  public static int[] convert(String [] sList){
    int[] elems = new int[sList.length];
    for (int i = 0; i < sList.length; i++){
      elems[i] = Integer.parseInt(sList[i]);
    }
    return elems;
  }
  public static void main(String [] args){
    StudentInfo.display();
    int[] elems = convert(args);
    System.out.println(countPositive(elems));
  }
}