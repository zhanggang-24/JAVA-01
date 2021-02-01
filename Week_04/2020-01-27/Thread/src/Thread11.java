import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池，<T> Future submit(Callable task),get()获取执行结果
 */

public class Thread11 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        long start = System.currentTimeMillis();

        AtomicInteger result = new AtomicInteger();
        ExecutorService executor = Executors.newFixedThreadPool(1);

        Future<Object> submit = executor.submit(() -> sum());

        result.set((Integer) submit.get());
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
