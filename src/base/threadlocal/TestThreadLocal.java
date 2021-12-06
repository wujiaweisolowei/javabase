package base.threadlocal;

import java.io.IOException;

/**threadlocal 的理解 其实是每个线程独享一个threadlocalMap， 而key 保存的是 tl对象。
 * @author 10192
 */
public class TestThreadLocal {

    static ThreadLocal<String> tl = new ThreadLocal<>();
    static ThreadLocal<String> tl_tmp = new ThreadLocal<>();
    public static void main(String[] args) throws Exception {
        System.out.println(tl);
        new Thread(()->{
            ThreadLocal<String> tl2 = tl;
            Thread t = Thread.currentThread();
            tl.set("a");  //set的时候会遇到hash冲突 nextIndex 来寻找下一个相邻的位置
            tl = null;
            System.gc(); //此时gc 会使key 为 null。
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl = tl2;
            System.out.println(tl_tmp.get()); // int i = key.threadLocalHashCode & (table.length - 1);
            // 如果没有则会寻找下一个相邻的，要保证e不为null且key相同
        }).start();
        System.out.println(tl);
    }
}
