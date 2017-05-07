package com.young.distributed.core.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Created by yangyong on 17-5-7.
 */
public class DThreadPool {

    private static final Logger log = LoggerFactory.getLogger(DThreadPool.class);

    private ThreadPoolExecutor threadPool;

    public DThreadPool(int core, int max, long alived, TimeUnit timeUnit, BlockingQueue<Runnable> queue) {
        this.threadPool = new ThreadPoolExecutor(core, max, alived, timeUnit, queue);
    }

    public DThreadPool() {
        this(20, 20, 1, TimeUnit.HOURS, new LinkedBlockingQueue<Runnable>());
    }

    public void execute(Runnable run) {
        threadPool.execute(run);
    }

    public void submit(Runnable run) {
        threadPool.submit(run);
    }

    public <V> Future<V> submit(Callable<V> call) {
        return threadPool.submit(call);
    }

    public void shutdown(boolean now) {
        if (now)
            threadPool.shutdownNow();
        else
            threadPool.shutdown();
    }

    public void monitor(int wait, TimeUnit unit, int timeout) throws InterruptedException {
        long start = System.currentTimeMillis();
        int count = 0;
        while (true) {
            if (threadPool.awaitTermination(wait, unit)) {
                log.info("thread pool executor over cost time -" + (System.currentTimeMillis() - start));
                break;
            } else {
                count++;
                if (count == timeout) {
                    log.info("thread pool shutdown timeout force shutdown,thread pool run cost time -" + (System.currentTimeMillis() - start));
                    shutdown(true);
                    break;
                }else {
                    log.info(" shut down threadpool false,wait time  "+wait +" " + unit.name());
                }
            }
        }
    }
}
