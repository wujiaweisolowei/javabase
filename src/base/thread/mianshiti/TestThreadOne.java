package base.thread.mianshiti;

/**
 * 多线程交互
 * 1.循环开销 + 可见性
 *
 */
public class TestThreadOne {

    private static volatile boolean flag = true;  //是否奇数
    private static int count =1 ;
    private static int count2 =1 ;
    public static void main(String[] args) {

        new Thread(()->{
            while (count < 100) {
                count2++; //6000+
                if (flag) {
                    System.out.println("当前线程"+Thread.currentThread().getName()+"---: "+count);
                    count++;
                    flag = false;
                }

            }
        }).start();

        new Thread(()->{
            while (count <= 100) {
                if (!flag) {
                    System.out.println("当前线程"+Thread.currentThread().getName()+"---: "+count);
                    count++;
                    flag = true;
                }
            }
        }).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count2);
    }


}
