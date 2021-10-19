package base.lock;

import java.util.concurrent.CountDownLatch;

/**
 * countdownlatch用法，等待子任务执行完毕后，主线程再执行自己的业务
 * 模拟5个学生先离开教室后，老师再离开教室
 */
public class TestCountDownLatch {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            final int tmp = i;
            new Thread(()->{
                System.out.println("学生 ："+tmp + "离开教室");
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("老师离开教室");
    }
}
