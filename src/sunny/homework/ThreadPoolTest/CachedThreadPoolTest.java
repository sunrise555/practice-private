package sunny.homework.ThreadPoolTest;

import java.util.concurrent.*;

/**
 *
 * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 * 线程在销毁前，可以复用
 *
 * default:
 *      return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
         60L, TimeUnit.SECONDS,
         new SynchronousQueue<Runnable>());
 *
 * Created by sunny on 2018/4/2
 */
public class CachedThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();

        for (int i = 0;i<10;i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000); // s睡眠index秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index + ":" + Thread.currentThread().getName());
                }
            });
        }

        // 输出
//        0:pool-1-thread-1
//        1:pool-1-thread-1
//        2:pool-1-thread-1
//        3:pool-1-thread-1
//        4:pool-1-thread-1
//        5:pool-1-thread-1
//        6:pool-1-thread-1

        //线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程


    }

}
