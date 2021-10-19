package base.lock;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟抢红包
 */
class HongBao{
    private static volatile Set<String> users = new HashSet<>();
    //红包金额
    private volatile BigDecimal account;
    //红包个数
    private volatile Integer count;

    private volatile List<BigDecimal> accountList = new ArrayList<>();

    HongBao(BigDecimal account,Integer count) {
        this.account = account;
        this.count = count;
        this.setPersonalAccountList();
    }
    //初始化每人抢到的金额，然后打乱顺序，保证红包公平性
    public void setPersonalAccountList() {
        BigDecimal tmpTotal = account;
        int c = count;
        while (c > 1) {
            BigDecimal bd = new BigDecimal(Math.random());
            BigDecimal getcount = tmpTotal.multiply(bd);
            getcount   =   getcount.setScale(2,BigDecimal.ROUND_HALF_UP);
            accountList.add(getcount);
            tmpTotal = tmpTotal.subtract(getcount);
            c--;
        }
        accountList.add(tmpTotal);
        Collections.shuffle(accountList);
    }
    public void getHongBao() {
        if (users.contains(Thread.currentThread().getName())) {
            System.out.println(Thread.currentThread().getName()+"\t已经抢过了");
        }
        if (count > 0) {
            BigDecimal getcount = accountList.get(count-1);
            System.out.println(Thread.currentThread().getName()+"\t抢到了"+getcount);
            count--;
            users.add(Thread.currentThread().getName());
        }else{
            System.out.println("对不起，红包已经抢完了");
        }

    }
}
public class TestSemaphore {
    public static void main(String[] args) {
        HongBao hongBao = new HongBao(new BigDecimal(40.00), 5);
        Semaphore semaphore = new Semaphore(5);
        ReentrantLock lock = new ReentrantLock(true);
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(100);
                    semaphore.acquire(); //只能控制最多进来5个人，不能控制原子操作
                    try {
                        lock.lock();
                        hongBao.getHongBao();
                    } catch (Exception e) {

                    }finally {
                        lock.unlock();
                    }
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }, "线程" + i).start();
        }
    }
}
