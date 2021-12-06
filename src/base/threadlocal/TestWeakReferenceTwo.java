package base.threadlocal;

import java.lang.ref.WeakReference;

/**
 *
 * 测试结果： 一个对象是可以同时指向弱引用和强引用的，当强引用存在时，gc时不回收
 * @author 10192
 */
class Car{

}

public class TestWeakReferenceTwo {

    public static void main(String[] args) {
        Car car = new Car();
        WeakReference<Car> weakReference = new WeakReference<>(new Car());
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());
    }
}
