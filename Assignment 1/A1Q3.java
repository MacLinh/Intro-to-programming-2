public class A1Q3{
  private static int getLongestRun(int[] elems){
    if (elems.length == 0)
      return 0;
    int record = 1, count = 1;
    for (int i =0; i < elems.length-1;i++){
      if (elems[i] == elems[i+1]){
        count++;
        if (count > record)
          record = count;
      }
      else
        count = 1;
    }
    return record;
  }
  public static void main(String [] args){
    StudentInfo.display();
    int[] elems = A1Q1.convert(args);
    System.out.println(getLongestRun(elems));
  }
}