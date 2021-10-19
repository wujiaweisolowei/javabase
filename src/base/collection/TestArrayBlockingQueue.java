package base.collection;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 测试ArrayBlockingQueue
 */
public class TestArrayBlockingQueue {

    public static void main(String[] args) throws Exception{
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);

        System.out.println(queue.add(1));
        System.out.println(queue.add(2));
        System.out.println(queue.add(3));
        //System.out.println(queue.add(4)); //   add  超出报错
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
//        System.out.println(queue.remove());   remove 超出 报错

        System.out.println(queue.offer(1));
        System.out.println(queue.offer(2));
        System.out.println(queue.offer(3));
        System.out.println(queue.offer(4)); // 超出 返回false

        System.out.println(queue.peek()); //查看头部第一个元素
        System.out.println(queue.poll());  //取出第一个元素
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());  //超出 返回null

        queue.put(1);
        queue.put(2);
        queue.put(3);
        System.out.println("准备第4个");
        new Thread(()->{
            try {
                Thread.sleep(2000); //2s后，取出一个元素，那么put第四个线程才不阻塞
                queue.take();
                Thread.sleep(1000);
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        queue.put(4);
        System.out.println("准备第5个");  //线程阻塞了
        queue.put(5);
        System.out.println("准备第6个");  //线程阻塞了

    }
}
