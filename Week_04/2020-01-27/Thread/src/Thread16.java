import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 定义AtomicBoolean类型的变量，子线程中设置值，主线程判断
 */

public class Thread16 {

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        AtomicBoolean flag = new AtomicBoolean(false);

        AtomicInteger result = new AtomicInteger();
        Thread thread = new Thread(() -> {
            result.set(sum());
            flag.set(true);
        });
        thread.start();

        while (!flag.get()) {
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
