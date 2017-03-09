public class ArrayStack implements Stack<DotInfo> { 
    private int max = 144;
    private DotInfo[] dots = new DotInfo[max];
    private int index = -1; // the top of the stack
    
    @Override
    public boolean isEmpty(){
        return index == -1;
    }
    
    @Override
    public DotInfo peek(){
        return dots[index];
    }
    
    @Override
    public DotInfo pop(){
        return dots[index--];//I will assume the stack is not empty because i am the only one using it
    }
    
    @Override
    public void push(DotInfo dot){
        if(index == max-1){// expand
            DotInfo[] tmp = new DotInfo[max *= 2];
            for(int i = 0; i < dots.length; i++){
                tmp[i] = dots[i]; // copies
            }
            dots = tmp;
        }
        dots[++index] = dot;
    }
}