package base.thread.mianshiti;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock coodition await signalAll
 */
class CountNumber{
    private static volatile Integer count = 1;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void printlnOddNumber() {
        try {
            lock.lock();
            while (count % 2 != 1) { //偶数阻塞
                condition.await();
            }
            System.out.println(Thread.currentThread().getName()+"="+count);
            count++;
            condition.signalAll();
        } catch (Exception e) {

        }finally {
            lock.unlock();
        }
    }
    public void printlnEvenNumber() {
        try {
            lock.lock();
            while (count % 2 == 1) { //偶数阻塞
                condition.await();
            }
            System.out.println(Thread.currentThread().getName()+"="+count);
            count++;
            condition.signalAll();
        } catch (Exception e) {

        }finally {
            lock.unlock();
        }
    }
}
public class TestThreadSix {
    public static void main(String[] args) {
        CountNumber countNumber = new CountNumber();
        new Thread(()->{
            int a = 0;
            while (a<50) {
                countNumber.printlnOddNumber();
                a++;
            }
        },"线程A").start();


        new Thread(()->{
            int a = 0;
            while (a<50) {
                countNumber.printlnEvenNumber();
                a++;
            }
        },"线程B").start();
    }
}
