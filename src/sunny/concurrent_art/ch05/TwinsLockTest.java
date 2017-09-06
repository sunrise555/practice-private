package sunny.concurrent_art.ch05;

import java.util.concurrent.locks.Lock;

/**
 * Created by Sunny on 2017/9/4.
 */
public class TwinsLockTest {
    public void test() throws InterruptedException{
        final Lock lock = new TwinsLock();

        class Worker extends Thread {
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        sleep(1000);
                        System.out.println(Thread.currentThread().getName());
                        sleep(1000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
        // ����10���߳�
        for (int i = 0; i < 10 ; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }
        // ÿ��1�뻻��
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println();
        }
    }
}
