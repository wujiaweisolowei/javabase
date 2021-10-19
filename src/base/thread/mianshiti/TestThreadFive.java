package base.thread.mianshiti;

/**
 * 通过线程之间的通信 实现 交替打印
 * 打印奇数与打印偶数方法，互相阻塞，互相唤醒。
 * sync  wait  notifyAll  铁三角
 */
class Count{
    private static volatile Integer count = 1;

    public void printlnOddNumber() {
        Class c=Count.class;
        synchronized (c) {
            while (count % 2 != 1) { //偶数阻塞
                try {
                    c.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"="+count);
            count++;
            c.notifyAll();
        }
    }
    public void printlnEvenNumber() {
        Class c = Count.class;
        synchronized (c) {
            while (count % 2 == 1) {  //奇数阻塞
                try {
                    c.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //打印偶数
            System.out.println(Thread.currentThread().getName()+"="+count);
            count++;
            c.notifyAll();
        }
    }
}
public class TestThreadFive {



    public static void main(String[] args) {

        Count count = new Count();
        new Thread(()->{
            int a = 0;
            while (a<50) {
                count.printlnOddNumber();
                a++;
            }
        },"线程A").start();


        new Thread(()->{
            int a = 0;
            while (a<50) {
                count.printlnEvenNumber();
                a++;
            }
        },"线程B").start();
    }
}
