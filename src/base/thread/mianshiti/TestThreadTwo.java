package base.thread.mianshiti;

/**
 * 线程交互打印
 * 2.利用同步块 保证代码同步 即原子操作
 */
public class TestThreadTwo {

    private static Integer count =1;
    private static Integer count2 =1;
    private static Integer count3 =1;

    public static void main(String[] args) {
        new Thread(() -> {
            while (count < 100) {
                count2++; //5000+
                synchronized (count) {
//                    count3++; //count3 = count2
                    if (count % 2 == 1) {
                        System.out.println("当前线程"+Thread.currentThread().getName()+"---: "+count);
                        count++;
                    }
                }
            }


        }, "奇数线程").start();

        new Thread(() -> {
            while (count <=100) {
                synchronized (count) {
                    if (count % 2 == 0) {
                        System.out.println("当前线程"+Thread.currentThread().getName()+"---: "+count);
                        count++;
                    }
                }
            }

        }, "偶数线程").start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count2);
//        System.out.println(count3);
    }
}
