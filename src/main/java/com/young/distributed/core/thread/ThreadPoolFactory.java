package com.young.distributed.core.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangyong on 17-5-7.
 */
public class ThreadPoolFactory {
    private static final DThreadPool threadPool = new DThreadPool(10000, 10000, 1, TimeUnit.HOURS, new LinkedBlockingQueue<Runnable>());

    private static final LoopThreadPool loopThreadPool = new LoopThreadPool();

    public static DThreadPool getSystemThreadPool(){
        return threadPool;
    }

    public static LoopThreadPool getLoopThreadPool(){
        return loopThreadPool;
    }

}
