package base.lock;

import java.util.concurrent.CountDownLatch;

class DataDemo2{
    volatile int num = 2;

    public void addUnm() {
        this.num++;
    }
}

/**
 * 严重volatile不能保证原子性
 * n++操作被拆分成3个指令（读，更新，写），线程执行3个指令期间，
 * 有被挂起来的可能，没有及时接受其他线程更新主存区变量的通知，更新后再次写入出现了写覆盖。
 */
public class TestVolatile2 {

    public static void main(String[] args) {
        DataDemo2 dataDemo2 = new DataDemo2();
        CountDownLatch count = new CountDownLatch(20);
        for (int i = 0; i <20 ; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    dataDemo2.addUnm();
                }
                count.countDown();
            }).start();
        }
        System.out.println("main线程等待子线程执行结束");
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("20 thread is over ,the num = " + dataDemo2.num);
    }
}
