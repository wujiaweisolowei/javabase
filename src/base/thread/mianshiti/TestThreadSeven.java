package base.thread.mianshiti;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 3个线程交互，线程A 打印1次，线程B打印2次，线程C打印3次，循环10次
 *
 * 需要使用condition的精确唤醒
 */

class ThreadPrintln{
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private static volatile Integer flag=1; //1表示线程A ，2表示线程B 3 表示线程C

    public void printlnOne() {
        try {
            lock.lock();
            while (flag != 1) {
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName());
            flag = 2;
            condition2.signal();
        } catch (Exception e) {
        }finally {
            lock.unlock();
        }
    }
    public void printlnTwo() {
        try {
            lock.lock();
            while (flag != 2) {
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName());
            flag = 3;
            condition3.signalAll();
        } catch (Exception e) {
        }finally {
            lock.unlock();
        }
    }
    public void printlnThree() {
        try {
            lock.lock();
            while (flag != 3) {
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName());
            flag = 1;
            condition1.signalAll();
        } catch (Exception e) {
        }finally {
            lock.unlock();
        }
    }

}
public class TestThreadSeven {

    public static void main(String[] args) {
        ThreadPrintln tp = new ThreadPrintln();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                tp.printlnOne();
            }
        },"线程A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                tp.printlnTwo();
            }
        },"线程B").start();


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                tp.printlnThree();
            }
        },"线程C").start();
    }
}
