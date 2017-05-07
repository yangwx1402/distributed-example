package com.young.distributed.core.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yangyong on 17-5-6.
 */
public class AnnotationUtils {

    public static Annotation getAnnotation(Class sourceClass,Class annotation){
        return sourceClass.getAnnotation(annotation);
    }

    public static <T> T getAnnotationValue(Annotation annotation,String field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = annotation.getClass().getMethod(field);
        return (T) method.invoke(annotation);
    }
}
