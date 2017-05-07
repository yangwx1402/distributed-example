package com.young.distributed.core.annotation.support

import com.young.distributed.core.annotation.support.base.{AnnotationProcess, AnnotationProcessEntity}
import com.young.distributed.core.annotation.support.exception.AnnotationException
import com.young.distributed.core.reflect.{AnnotationUtils, ClassUtils}
import com.young.distributed.core.thread.ThreadPoolFactory
import com.young.distributed.core.thread.support.DLoopThread
import org.slf4j.LoggerFactory

/**
  * Created by yangyong on 17-5-6.
  */
class LoopThreadAnnotationProcess extends AnnotationProcess {

  private val log = LoggerFactory.getLogger(classOf[LoopThreadAnnotationProcess])

  private val DEFAULT_THREAD_FIELD = "threads"

  private val DEFAULT_THREAD_NAME = "name"

  @throws[AnnotationException]
  override def checkAnnotation[T](annotationProcessEntity: AnnotationProcessEntity[T]): Unit = {
    super.checkAnnotation(annotationProcessEntity)
  }

  @throws[AnnotationException]
  override def process[T](annotationProcessEntitys: AnnotationProcessEntity[T]*): Unit = annotationProcessEntitys.foreach(entity => {
    checkAnnotation(entity)
    val annotation = entity.annotation
    val target = entity.target
    val threadSize: Int = AnnotationUtils.getAnnotationValue(annotation, DEFAULT_THREAD_FIELD)
    val threadName: String = AnnotationUtils.getAnnotationValue(annotation, DEFAULT_THREAD_NAME)
    var runnable: DLoopThread = null
    for (i <- 0 until threadSize) {
      if (entity.targetArgs.size > 0) {
        val targetArgsClass : Array[Class[_]] = entity.targetArgs.map(arg => arg.getClass)
        runnable = ClassUtils.newInstance(target,entity.targetArgs,targetArgsClass).asInstanceOf[DLoopThread]
      }else{
        runnable = ClassUtils.newInstance(target).asInstanceOf[DLoopThread]
      }
      val thread: Thread = new Thread(runnable)
      thread.setName(threadName + "_" + (i))
      ThreadPoolFactory.getLoopThreadPool.submit(runnable)
      log.info("start thread name is [" + thread.getName + "]")
    }
  })
}
