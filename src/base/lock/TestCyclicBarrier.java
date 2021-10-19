package base.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 的应用场景是 当多个线程执行完任务后，阻塞线程才能继续
 * 集齐七龙珠，召唤神龙
 */
public class TestCyclicBarrier {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(7,()->{

            System.out.println("召唤神龙");
        });

        for (int i = 0; i < 7; i++) {
            final int tmp = i;
            new Thread(()->{
                System.out.println("龙珠"+(tmp+1)+"号已经就位");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
