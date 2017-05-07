package com.young.distributed.core.annotation.thread

import com.young.distributed.core.annotation.ThreadInfo
import com.young.distributed.core.annotation.support.ThreadInfoAnnotationProcess
import com.young.distributed.core.reflect.AnnotationUtils
import com.young.distributed.core.thread.support.LoopThread

/**
  * Created by yangyong on 17-5-7.
  */
@ThreadInfo(threads = 2,name = "test_thread")
class TestThread extends LoopThread{


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
    val annotation = AnnotationUtils.getAnnotation(target,classOf[ThreadInfo])
    val process = new ThreadInfoAnnotationProcess
    process.process(target,annotation)
  }
}
