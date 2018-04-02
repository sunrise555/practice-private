package sunny.homework.ThreadPoolTest;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
     super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
     new DelayedWorkQueue());
 *
 * 创建一个定长线程池，支持定时及周期性任务执行
 * Created by sunny on 2018/4/2
 */
public class ScheduledThreadPoolTest {

    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);

        /**
         * 延迟执行
         */
//        pool.schedule(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("delay 3 seconds");
//            }
//        },3, TimeUnit.SECONDS); // 延迟3秒

        /**
         * 周期执行
         */
        pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 0s, and work per 10ms");
                System.out.println(Thread.currentThread().getName());
            }
        },0,1,TimeUnit.MILLISECONDS); // 延迟1秒，每隔3秒执行
    }
}
