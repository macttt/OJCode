package Thread;


public class TestYield implements Runnable{
    static int threadCount = 1;
    private final int id = threadCount++;
    private int yCount = 5;
    public TestYield(){

    }
    public TestYield(int yCount){
        this.yCount = yCount;
    }
    public void run(){
        for(int i=0;i<yCount;i++){
            System.out.println("#"+id+"-count"+i);
            Thread.yield();
        }
        System.out.println("#"+id+"-over");
    }

    public static void main(String[] args){
        for(int i=0;i<10;i++){
            new Thread(new TestYield()).start();
        }
    }
}
