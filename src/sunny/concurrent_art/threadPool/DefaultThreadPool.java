package concurrent_art.threadPool;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntPredicate;

/**
 * 线程池的默认实现
 * Created by Sunny on 2017/8/19.
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job>{
    private final static int MAX_WORKER_NUMBERS = 10;
    private final static int DEFAULT_WORKER_NUMBERS = 5;
    private final static int MIN_WORKER_NUMBERS = 1;

    // 任务列表
    private final LinkedList<Job> jobs = new LinkedList<Job>();
    // 线程列表
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<>());

    private int workerNum = DEFAULT_WORKER_NUMBERS;

    // 线程编号
    private AtomicInteger threadNum = new AtomicInteger();

    public DefaultThreadPool() {
        initialWorkers(DEFAULT_WORKER_NUMBERS);
    }

    public DefaultThreadPool(int num) {
        this.workerNum = num > MAX_WORKER_NUMBERS ?
                MAX_WORKER_NUMBERS:num < MIN_WORKER_NUMBERS?MIN_WORKER_NUMBERS:num;
        initialWorkers(workerNum);
    }

    @Override
    public void execute(Job job) {
        if (job != null) {
            synchronized (jobs) {
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }

    @Override
    public void shutdown() {
        for (Worker worker:workers) {
            worker.shutdown();
        }
    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs) {
            if (num + this.workerNum > MAX_WORKER_NUMBERS)
                num = MAX_WORKER_NUMBERS - this.workerNum;
            initialWorkers(num);
            this.workerNum += num;
        }
    }

    @Override
    public void removeWorkers(int num) {
        synchronized (jobs) {
            if (this.workerNum < num)
                throw new IllegalArgumentException("beyond workNum");

            int count = 0;
            while (count < num) {
                Worker worker = workers.get(count);
                if (workers.remove(worker)) {
                    worker.shutdown();
                    count++;
                }
            }
            this.workerNum -= count;
        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }

    // 初始化线程工作者
    private void initialWorkers(int num) {
        for (int i = 0; i < num ; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker,"ThreadPool-Worker-"
                    + threadNum.incrementAndGet());
            thread.start();
        }
    }

    class Worker implements Runnable {
        private volatile boolean running = true;

        @Override
        public void run() {
            Job job = null;
            synchronized (jobs){
                while (running) {
                    // 若任务列表为空则继续等待
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job = jobs.removeFirst();
                }
            }
            if (job != null) {
                try {
                    job.run();
                } catch (Exception e) {
                    // TODO: 2017/8/19
                }
            }
        }

        public void shutdown() {
            running = false;
        }
    }
}
