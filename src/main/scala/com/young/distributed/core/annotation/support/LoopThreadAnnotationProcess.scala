package com.young.distributed.core.annotation.support

import java.lang.annotation.Annotation

import com.young.distributed.core.annotation.LoopThread
import com.young.distributed.core.annotation.support.base.TargetAnnotationProcess
import com.young.distributed.core.reflect.{AnnotationUtils, ClassUtils}
import com.young.distributed.core.thread.support.DLoopThread
import com.young.distributed.core.thread.{LoopThreadPool, ThreadPoolFactory}
import org.slf4j.LoggerFactory

/**
  * Created by yangyong on 17-5-6.
  */
class LoopThreadAnnotationProcess extends TargetAnnotationProcess {

  private val log = LoggerFactory.getLogger(classOf[LoopThreadAnnotationProcess])

  private val DEFAULT_THREAD_FIELD = "threads"

  private val DEFAULT_THREAD_NAME = "name"

  override def process[T <: AnyRef](target: Class[T], annotation: Annotation): Unit = {
    val threadSize :Int = AnnotationUtils.getAnnotationValue(annotation, DEFAULT_THREAD_FIELD)
    val threadName: String = AnnotationUtils.getAnnotationValue(annotation, DEFAULT_THREAD_NAME)
    for (i <- 0 until threadSize) {
      val runnable: DLoopThread = ClassUtils.newInstance(target).asInstanceOf[DLoopThread]
      val thread: Thread = new Thread(runnable)
      thread.setName(threadName + "_" + (i))
      ThreadPoolFactory.getLoopThreadPool.submit(runnable)
      log.info("start thread name is ["+thread.getName+"]")
    }
  }
}
