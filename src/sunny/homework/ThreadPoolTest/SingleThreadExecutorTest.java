package sunny.homework.ThreadPoolTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 1. newSingleThreadExecutor

     创建一个单线程的线程池。这个线程池只有一个线程在工作，
     也就是相当于单线程串行执行所有任务。如果这个唯一的线程因为异常结束，
     那么会有一个新的线程来替代它。此线程池保证所有任务的执行顺序按照任务的提交顺序执行。

     return new FinalizableDelegatedExecutorService
     (new ThreadPoolExecutor(1, 1,
     0L, TimeUnit.MILLISECONDS,
     new LinkedBlockingQueue<Runnable>()));


 * Created by sunny on 2018/4/2
 */
public class SingleThreadExecutorTest {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newSingleThreadExecutor();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int n = 1000;
                while (true) {
                    System.out.println("1");
                    n--;
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int n = 1000;
                while (n > 0) {
                    System.out.println("2");
                    n--;
                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("3");
            }
        });

        pool.submit(thread1);
        pool.submit(thread2);
        pool.submit(thread3);
        // 输出 1，2，3
    }
}
