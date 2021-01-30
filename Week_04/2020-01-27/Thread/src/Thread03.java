import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport，在主线程中调用park阻塞主线程，在子线程中调用unpark唤醒主线程
 */

public class Thread03 {
    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        final Thread manThread = Thread.currentThread();
        AtomicInteger result = new AtomicInteger();
        Thread thread = new Thread(() -> {
                result.set(sum());
            LockSupport.unpark(manThread);
        });
        thread.start();

        LockSupport.park(manThread);
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
