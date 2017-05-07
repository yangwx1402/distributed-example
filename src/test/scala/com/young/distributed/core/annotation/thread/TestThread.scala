package com.young.distributed.core.annotation.thread

import java.util.concurrent.TimeUnit

import com.young.distributed.core.annotation.LoopThread
import com.young.distributed.core.annotation.support.LoopThreadAnnotationProcess
import com.young.distributed.core.annotation.support.base.AnnotationProcessEntity
import com.young.distributed.core.reflect.AnnotationUtils
import com.young.distributed.core.thread.ThreadPoolFactory
import com.young.distributed.core.thread.support.DLoopThread

/**
  * Created by yangyong on 17-5-7.
  */
@LoopThread(threads = 2,name = "test_thread")
class TestThread(message:String) extends DLoopThread{


  private var loopFLag = true

  override def stop(): Unit = loopFLag = false

  override def task(): Unit = {
    println(message)
  }

  override def interval(): Long = 2000

  override def loop(): Boolean = loopFLag
}
object TestThread{
  def main(args: Array[String]): Unit = {
    val target = classOf[TestThread]
    val annotation = AnnotationUtils.getAnnotation(target,classOf[LoopThread])
    val process = new LoopThreadAnnotationProcess
    val args = Array[AnyRef]("yangyong")
    process.process(AnnotationProcessEntity(target,annotation,args))
    Thread.sleep(1000)
    ThreadPoolFactory.getLoopThreadPool.stopPool()
    ThreadPoolFactory.getSystemThreadPool.shutdown(false)
    ThreadPoolFactory.getSystemThreadPool.monitor(1,TimeUnit.MINUTES,1)
  }
}
