import java.util.concurrent.atomic.AtomicInteger;

/**
 * 主线程中循环判断子线程：State,不是TERMINATED就等待
 */

public class Thread08 {
    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        AtomicInteger result = new AtomicInteger();
        Thread thread = new Thread(() -> {
            result.set(sum());
        });
        thread.start();
        while (!thread.getState().equals(Thread.State.TERMINATED)) {
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
