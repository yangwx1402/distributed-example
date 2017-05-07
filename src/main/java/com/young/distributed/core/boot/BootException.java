package com.young.distributed.core.boot;

/**
 * Created by yangyong on 17-5-7.
 */
public class BootException extends Exception{

    public BootException(String message,Throwable throwable){
        super(message,throwable);
    }

    public BootException(String message){
        super(message);
    }
    public BootException(Throwable throwable){
        super(throwable);
    }
}
