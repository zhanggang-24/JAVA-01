import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock/condition
 */

public class Thread15 {
    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        Lock lock = new ReentrantLock(true);
        Condition condition = lock.newCondition();

        AtomicInteger result = new AtomicInteger();
        Thread thread = new Thread(() -> {
            try {
                lock.lock();
                result.set(sum());
                condition.signalAll();
            } finally {
                lock.unlock();
            }

        });
        try {
            lock.lock();
            thread.start();
            condition.await();
        } finally {
            lock.unlock();
        }

        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
