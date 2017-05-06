package com.young.distributed.core.reflect;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by yangyong on 17-5-6.
 */
public class ClassUtils {
    public static <T> T newInstance(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

    public static <T> T newInstance(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<T> tClass = (Class<T>) Class.forName(className);
        return newInstance(tClass);
    }

    public static <T> T newInstance(Class<T> tClass,Object[] args,Class... parameters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return tClass.getConstructor(parameters).newInstance(args);
    }

    public static <T> T newInstance(String className,Object[] args,Class... parameters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        Class<T> tClass = (Class<T>) Class.forName(className);
        return newInstance(tClass,args,parameters);
    }
}
