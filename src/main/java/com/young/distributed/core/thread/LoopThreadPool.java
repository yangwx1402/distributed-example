package com.young.distributed.core.thread;

import com.young.distributed.core.thread.support.DLoopThread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyong on 17-5-7.
 */
public class LoopThreadPool{

    private List<DLoopThread> threads = new ArrayList<DLoopThread>();

    public synchronized void submit(DLoopThread thread){
        threads.add(thread);
        ThreadPoolFactory.getSystemThreadPool().submit(thread);
    }

    public synchronized void stopPool(){
        for(DLoopThread thread:threads){
            thread.stop();
        }
    }
}
