package base;

public class TestInteger {

    public static void main(String[] args) {
        Integer a = 100,  b=100 ,c = 129,d=129;

        System.out.println(a == b); //true  因为在-128 -127之间有缓存
        System.out.println(c == d); //false
    }
}
