package com.young.distributed.core.annotation.support

import java.lang.annotation.Annotation

import com.young.distributed.core.annotation.support.base.TargetAnnotationProcess
import com.young.distributed.core.reflect.{AnnotationUtils, ClassUtils}
import com.young.distributed.core.thread.DefaultThreadPool
import org.slf4j.LoggerFactory

/**
  * Created by yangyong on 17-5-6.
  */
class ThreadInfoAnnotationProcess extends TargetAnnotationProcess {

  private val log = LoggerFactory.getLogger(classOf[ThreadInfoAnnotationProcess])

  private val DEFAULT_THREAD_FIELD = "threads"

  private val DEFAULT_THREAD_NAME = "name"

  override def process[T <: AnyRef](target: Class[T], annotation: Annotation): Unit = {
    val threadSize :Int = AnnotationUtils.getAnnotationValue(annotation, DEFAULT_THREAD_FIELD)
    val threadName: String = AnnotationUtils.getAnnotationValue(annotation, DEFAULT_THREAD_NAME)
    for (i <- 0 until threadSize) {
      val runnable: Runnable = ClassUtils.newInstance(target).asInstanceOf[Runnable]
      val thread: Thread = new Thread(runnable)
      thread.setName(threadName + "_" + (i))
      DefaultThreadPool.getSystemThreadPool.submit(thread)
      log.info("start thread name is ["+thread.getName+"]")
    }
  }
}
