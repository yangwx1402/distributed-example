package com.young.distributed.core.boot;

/**
 * Created by yangyong on 17-5-7.
 */
public interface Boot {
    public void run(Class... clazz) throws BootException;
}
