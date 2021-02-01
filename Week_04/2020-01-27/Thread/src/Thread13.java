import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池，shutdown()->isTerminated()判断是否结束
 */

public class Thread13 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        long start = System.currentTimeMillis();

        AtomicInteger result = new AtomicInteger();
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(() -> {
            result.set(sum());
        });
        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        executor.shutdown();

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

