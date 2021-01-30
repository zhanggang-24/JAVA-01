import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CyclicBarrier
 */

public class Thread06 {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

        long start = System.currentTimeMillis();

        AtomicInteger result = new AtomicInteger();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        Thread thread = new Thread(() -> {
            result.set(sum());
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        cyclicBarrier.await();
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
