package base.thread.mianshiti;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程交互打印
 * 3.利用锁控制 代码同步  （注意代码逻辑包括判断一定在锁内）
 */
public class TestThreadThree {

    private static Integer count = 1;
    private static Integer count2 = 1;

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(()->{
            while (count < 100) {
                count2++;
                lock.lock();
                if (count % 2 == 1) {
                    System.out.println("当前线程"+Thread.currentThread().getName()+"---: "+count);
                    count++;
                }
                lock.unlock();
            }
        },"奇数线程").start();

        new Thread(()->{
            while (count <= 100) {
                lock.lock();
                if (count % 2 == 0) {
                    System.out.println("当前线程"+Thread.currentThread().getName()+"---: "+count);

                    count++;

                }
                lock.unlock();
            }
        },"偶数线程").start();


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count2);
    }
}
