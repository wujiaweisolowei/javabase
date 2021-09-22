package base.basecore.suanfa;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Test1 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Math.random();
        Integer e=sc.nextInt();
        Set set =new HashSet();
        set.add(e);

        set.stream().forEach(el->{
            System.out.println(el);
        });
    }

}
