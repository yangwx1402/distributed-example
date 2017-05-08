package com.young.distributed.core.task.lock

/**
  * Created by yangyong on 17-5-8.
  * 该接口的作用是实现一个分布式锁任务,分布式环境下只有一个进程/线程拿到锁并执行相关的任务
  */
trait DLockTask[T] {

  @throws[DLockTaskException]
  def runTask():Option[T]
}
