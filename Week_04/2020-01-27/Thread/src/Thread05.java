import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CountDownLatch
 */

public class Thread05 {
    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        AtomicInteger result = new AtomicInteger();
        CountDownLatch latch = new CountDownLatch(1);
        Thread thread = new Thread(() -> {
            result.set(sum());
            latch.countDown();
        });
        thread.start();
        latch.await();

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
