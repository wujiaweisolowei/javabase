package base.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 测试ArrayBlockingQueue
 */
public class TestArrayBlockingQueueTwo {

    public static void main(String[] args) throws Exception{
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);

        System.out.println(queue.offer(1,2, TimeUnit.SECONDS));
        System.out.println(queue.offer(2,2, TimeUnit.SECONDS));
        System.out.println(queue.offer(3,2, TimeUnit.SECONDS));
        System.out.println(queue.offer(4,2, TimeUnit.SECONDS)); //2s后没有添加成功，返回false


        System.out.println(queue.poll(2,TimeUnit.SECONDS));
        System.out.println(queue.poll(2,TimeUnit.SECONDS));
        System.out.println(queue.poll(2,TimeUnit.SECONDS));
        System.out.println(queue.poll(2,TimeUnit.SECONDS)); //2秒没有取出，返回null

    }
}
