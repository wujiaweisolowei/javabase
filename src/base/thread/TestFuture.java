package base.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class MyRunnabel implements Runnable {

    @Override
    public void run() {
        System.out.println("MyRunnabel is running");
    }
}

class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("MyCallable is running");
        return 1024;
    }
}
public class TestFuture {

    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallable());
        Thread thread = new Thread(futureTask, "aa");
        thread.start();
        
        
    }
}
