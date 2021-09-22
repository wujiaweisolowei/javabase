package base.lock;

class DataDemo{
    volatile int num = 0;
    public void addNum() {
        this.num = 60;
    }
}
/**
 * 严重volatile 的可见性，线程改变主存区变量时，及时通知main线程
 */
public class TestVolatile {
    public static void main(String[] args) {
        DataDemo dataDemo = new DataDemo();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    dataDemo.addNum();
                    System.out.println("update over num = " + dataDemo.num);
                }
            }).start();
        while (dataDemo.num == 0) {
        }
        System.out.println("main 线程跳出循环");
    }
}
