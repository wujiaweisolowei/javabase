package base.lock;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 验证ABA
 */
public class TestAtomicRefrence {

    public static void main(String[] args) {
        //testABA();
        invokeABA();




    }
    //添加版本号解决ABA
    public static void invokeABA() {
        AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(1,10);
        new Thread(()->{
            stampedReference.compareAndSet(1, 2, 10, 20);
            stampedReference.compareAndSet(2, 1, 20, 30);
        }).start();
        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stampedReference.compareAndSet(1, 2, 10, 30);
        }).start();
        try {
            Thread.sleep(1100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(stampedReference.getReference());
        System.out.println(stampedReference.getStamp());

    }
    public static void testABA() {
        AtomicReference<Integer> reference = new AtomicReference<>(10);
        new Thread(()->{
            reference.compareAndSet(10, 20);
            reference.compareAndSet(20, 10);
        }).start();

        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reference.compareAndSet(10, 30);
        }).start();

        try {
            Thread.sleep(1100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(reference.get());
    }
}
