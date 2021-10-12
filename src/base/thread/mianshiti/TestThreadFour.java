package base.thread.mianshiti;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁 完全提现线程排队
 */
public class TestThreadFour {

    private static int count=1;
    private static int count2=1;

    private static ReentrantLock failLock = new ReentrantLock(true);
    public static void main(String[] args) {

        new Thread(() -> {
            while (count < 100) {
                count2++; //51
                failLock.lock();
                System.out.println("当前线程"+Thread.currentThread().getName()+"---: "+count);
                count++;
                failLock.unlock();
            }
        }, "奇数").start();

        new Thread(() -> {
            while (count <= 100) {
                failLock.lock();
                System.out.println("当前线程"+Thread.currentThread().getName()+"---: "+count);
                count++;
                failLock.unlock();
            }
        }, "偶数").start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count2);
    }
}
