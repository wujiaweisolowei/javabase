package base.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 可重入锁
 */
class MyReetrantLock{
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private volatile Thread existThread=null;

    public void lock() {
        if(!tryLock()){

        }else{
            System.out.println(Thread.currentThread().getName() + "获取锁成功");
        }
    }

    public boolean tryLock() {
        Thread thread = Thread.currentThread();
       if(atomicInteger.compareAndSet(0, 1)){
           existThread = thread;
           return true;
       } else if (existThread == thread) {
           atomicInteger.incrementAndGet();
           return true;
       }else{
           return false;
       }
    }

    public void unlock() {
        if (existThread == Thread.currentThread() && atomicInteger.get()>0) {
            atomicInteger.decrementAndGet();
            if (atomicInteger.get() == 0) {
                existThread = null;
            }
            System.out.println(Thread.currentThread().getName() + "释放锁成功");
        }
    }

}
public class TestMyReentrantLock {

    public static void main(String[] args) {
        new Thread(()->{
            MyReetrantLock lock = new MyReetrantLock();
            lock.lock();
            lock.lock();
            lock.unlock();
            lock.unlock();
        },"线程A").start();
    }
}
