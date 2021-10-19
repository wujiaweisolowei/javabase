package base.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 写必须互斥
 * 读读可以并发
 */
public class TestReetrantWriteReadLock {

    private static volatile Map<String, Integer> cache = new HashMap<>();

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) {


        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(()->{
                get("线程：" + temp);
            },"线程："+i).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(()->{
                put("线程：" +temp, temp);
            },"线程："+i).start();
        }


//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }


    public static void put(String key,Integer value) {
//        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+": "+"开始写");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cache.put(key, value);
            System.out.println(Thread.currentThread().getName()+": "+"写完成");
        } catch (Exception e) {
        }finally {
            readWriteLock.writeLock().unlock();
        }



    }

    public static void get(String key) {
//        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+": "+"开始读");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cache.get(key);
            System.out.println(Thread.currentThread().getName() + ": " + "读完成" );
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }


    }
}
