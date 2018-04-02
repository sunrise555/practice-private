package sunny.homework.ThreadPoolTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
     return new ThreadPoolExecutor(nThreads, nThreads,
     0L, TimeUnit.MILLISECONDS,
     new LinkedBlockingQueue<Runnable>());


 * Created by sunny on 2018/4/2
 */
public class FixedThreadPoolTest {
    public static void main(String[] args) {
        // 固定3个线程

        ExecutorService pool = Executors.newFixedThreadPool(3);

        // 每2秒打印3个数字
        for (int i = 0;i<10;i++) {
            final int index = i;
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000); // 休眠2秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
