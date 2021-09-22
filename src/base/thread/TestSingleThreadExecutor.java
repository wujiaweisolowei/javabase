package base.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 单线程的线程池，核心线程数与最大线程数都是1
 */
public class TestSingleThreadExecutor {


    public static void main(String[] args) {
        ExecutorService es = Executors.newSingleThreadExecutor();
        ExecutorService es2= Executors.newFixedThreadPool(10);
        ExecutorService es3 = Executors.newCachedThreadPool();

        ReentrantLock lock = new ReentrantLock();

    }
}
