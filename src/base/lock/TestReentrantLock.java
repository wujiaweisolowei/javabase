package base.lock;

import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {
    ReentrantLock lock = new ReentrantLock(false);
    public static void main(String[] args) {
        new Thread(()->{
            new TestReentrantLock().set();
        }).start();

    }

    public void set() {
        try{
            lock.tryLock();
            lock.lock();
            get();
            System.out.println(Thread.currentThread().getName()+" set()");
        }finally {
            lock.unlock();
        }
    }

    public void get() {
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" get()");
        }finally {
            lock.unlock();
        }
    }
}
