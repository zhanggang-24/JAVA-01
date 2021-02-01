
import java.util.concurrent.*;

/**
 * 线程池，FutureTask
 */

public class Thread12 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        long start = System.currentTimeMillis();

        Integer result;
        ExecutorService executor = Executors.newFixedThreadPool(1);

        FutureTask<Integer> futureTask = new FutureTask<>(() -> sum());

        executor.submit(futureTask);

        result = futureTask.get();

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

