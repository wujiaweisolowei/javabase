package base.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

class DataDemo3{
    AtomicInteger num=new AtomicInteger(0);

    public void addNum() {
        //返回更新后的值
//        num.addAndGet(1);
        //返回更新前的值
        num.getAndAdd(1);

    }
}

public class TestAutomic {

    public static void main(String[] args) {
        DataDemo3 dataDemo3 = new DataDemo3();
        CountDownLatch latch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    dataDemo3.addNum();
                }
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行结束后 num = "+dataDemo3.num);
    }
}
