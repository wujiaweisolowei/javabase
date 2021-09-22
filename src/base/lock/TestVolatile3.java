package base.lock;

/**
 * 对象初始化的时候时分为散步
 * 1.分配内存空间
 * 2.实例化
 * 3.变量指向内存空间地址值
 *
 * 这里加上volatile 是为了 防止指令重排
 */
class SingleTon{
    private static volatile SingleTon singleTon = null;
    public SingleTon() {
        System.out.println(Thread.currentThread().getName()+" 进行了初始化");
    }

    public static SingleTon getInstance() {

        if (singleTon == null) {
            synchronized (SingleTon.class) {
                if (singleTon == null) {
                    singleTon = new SingleTon();
                }
            }
        }
        return singleTon;
    }
}

public class TestVolatile3 {

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() ->{
                SingleTon.getInstance();
            }).start();
        }
    }
}
