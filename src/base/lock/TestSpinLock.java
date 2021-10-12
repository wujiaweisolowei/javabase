package base.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

class MySpinLock{
    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock(Thread thread) {

        while (!atomicReference.compareAndSet(null, thread)) {
//            System.out.println("线程 "+ thread.getName()+"没有获取到锁，等待中----");
        }
        //保证释放锁之后先打印释放锁成功
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("抢锁成功----"+thread.getName());
    }

    public void unlock(Thread thread) {
        if (atomicReference.compareAndSet(thread, null)) {
            System.out.println("释放锁成功----"+thread.getName());
        }

    }
}

public class TestSpinLock {

    public static void main(String[] args) {
        MySpinLock mySpinLock = new MySpinLock();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                mySpinLock.lock(Thread.currentThread());

                //执行业务的时间
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    mySpinLock.unlock(Thread.currentThread());
                }
            }).start();

        }
    }

}
