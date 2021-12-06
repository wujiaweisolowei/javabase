package base.threadlocal;

import java.lang.ref.WeakReference;

class Person{

}
/**弱引用发现就清除，但是基本类型与string类型的常量 不会清除
 * @author 10192
 */
public class TestWeakReference {

    static WeakReference<String> wr = new WeakReference<String>(new String("a"));
    public static void main(String[] args) throws Exception {
//        WeakReference<Person> wr = new WeakReference<Person>(new Person());
        System.out.println(wr.get());
        System.gc();
        System.out.println(wr.get());
    }

}
