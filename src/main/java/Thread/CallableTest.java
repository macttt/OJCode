package Thread;

import java.util.ArrayList;
import java.util.concurrent.*;

public class CallableTest implements Callable<String> {
    static int threadCount = 1;
    private int id = threadCount++;
    public String call(){
        try{
            Thread.sleep(100*((int)(Math.random()*10)));

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "#"+id;
    }
}

class CallableDemo{
    public static void main(String[] args){
        ExecutorService es = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<>();
        for(int i=0;i<10;i++){
            results.add(es.submit(new CallableTest()));
        }
        int cnt=0;
        /**这里尝试给每个进程一个不同的等待时间，
         * 在等待时间过后把完成的进程打印出来。
         * 但是发现不知道怎么让打印过的进程不再打印。
         * 数组判断肯定可以，但是有缺陷，
         * 之后尝试用多线程的方式解决。
         * */
        while(true){
            if(cnt>=10){
                es.shutdown();
                break;
            }
            //
            for(Future<String> future:results){
                try {
                    if(future.isDone()){
                        cnt++;
                        System.out.println(future.get());
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }catch (ExecutionException e){
                    e.printStackTrace();
                }
            }
            try{
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }
}
