package com.young.distributed.core.thread.support;

/**
 * Created by yangyong on 17-5-7.
 */
public abstract class DLoopThread implements Runnable {

    public abstract boolean loop();

    public abstract void stop();

    public abstract void task() throws Exception;

    public abstract long interval();

    private void sleep() throws InterruptedException {
        long interval = interval();
        if (interval >= 0)
            Thread.sleep(interval);
    }

    public void run() {
        while (loop()) {
            try {
                task();
                sleep();
            } catch (Exception e) {
                try {
                    sleep();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
