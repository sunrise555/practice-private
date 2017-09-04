package concurrent_art.threadPool;

/**
 * �̳߳ؽӿ�
 * Created by Sunny on 2017/8/19.
 */
public interface ThreadPool<Job extends Runnable> {
    // ִ��һ��Job
    void execute(Job job);

    // �ر��̳߳�
    void shutdown();

    // �����߳�
    void addWorkers(int num);

    // �����߳�
    void removeWorkers(int num);

    // �õ�����ִ�е�job������
    int getJobSize();
}
