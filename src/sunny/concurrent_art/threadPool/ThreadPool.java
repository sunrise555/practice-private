package concurrent_art.threadPool;

/**
 * 线程池接口
 * Created by Sunny on 2017/8/19.
 */
public interface ThreadPool<Job extends Runnable> {
    // 执行一个Job
    void execute(Job job);

    // 关闭线程池
    void shutdown();

    // 增加线程
    void addWorkers(int num);

    // 减少线程
    void removeWorkers(int num);

    // 得到正在执行的job的数量
    int getJobSize();
}
