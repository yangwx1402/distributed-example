package com.young.distributed.core.annotation.thread

import com.young.distributed.core.annotation.LoopThread
import com.young.distributed.core.annotation.support.LoopThreadAnnotationProcess
import com.young.distributed.core.reflect.AnnotationUtils
import com.young.distributed.core.thread.ThreadPoolFactory
import com.young.distributed.core.thread.support.DLoopThread

/**
  * Created by yangyong on 17-5-7.
  */
@LoopThread(threads = 2,name = "test_thread")
class TestThread extends DLoopThread{


  private var loopFLag = true

  override def stop(): Unit = loopFLag = false

  override def task(): Unit = {
    println("haha")
  }

  override def interval(): Long = 100

  override def loop(): Boolean = loopFLag
}
object TestThread{
  def main(args: Array[String]): Unit = {
    val target = classOf[TestThread]
    val annotation = AnnotationUtils.getAnnotation(target,classOf[LoopThread])
    val process = new LoopThreadAnnotationProcess
    process.process(target,annotation)
    Thread.sleep(1000)
    ThreadPoolFactory.getLoopThreadPool.stopPool()
    ThreadPoolFactory.getSystemThreadPool.shutdown(false)
  }
}
