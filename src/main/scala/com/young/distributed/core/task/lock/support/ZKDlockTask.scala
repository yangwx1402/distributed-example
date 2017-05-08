package com.young.distributed.core.task.lock.support

import java.util.concurrent.TimeUnit

import com.typesafe.config.ConfigException.Null
import com.young.distributed.core.task.lock.{DLockTask, DLockTaskException}
import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.recipes.locks.InterProcessMutex

import scala.concurrent.duration.TimeUnit

/**
  * Created by yangyong on 17-5-8.
  */
abstract class ZKDlockTask[T](curatorFramework: CuratorFramework, lockPath: String = "/tmp", timeout: Int = 1, timeUnit: TimeUnit = TimeUnit.MINUTES) extends DLockTask[T] {

  @throws[DLockTaskException]
  def task(): T

  @throws[DLockTaskException]
  override def runTask(): Option[T] = {
    val lock = new InterProcessMutex(curatorFramework, lockPath + this.getClass().getName())
    if (lock.acquire(timeout, timeUnit)) {
      try {
        Option[T](task())
      } catch {
        case e: Exception => throw new DLockTaskException(e)
      } finally {
        lock.release()
      }
    } else {
      Option.empty
    }
  }
}
