import java.util.concurrent.atomic.AtomicInteger;

/**
 * wait()/notify()
 */

public class Thread04 {
    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        AtomicInteger result = new AtomicInteger();
        Object lock = new Object();

        Thread thread = new Thread(() -> {
            synchronized (lock) {
                result.set(sum());
                lock.notifyAll();
            }
        });

        synchronized (lock) {
            thread.start();
            lock.wait();
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

