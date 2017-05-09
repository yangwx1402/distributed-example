package com.young.distributed.core.task.lock.support

import java.util.concurrent.TimeUnit

import com.young.distributed.core.zk.ZKClient
import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock

/**
  * Created by yangyong on 17-5-9.
  * 读写锁,多读支持,但是读和写是互斥的,写而且是独占的
  */
class ZKDSharedReadWriteLock(curatorFramework: CuratorFramework, lockPath: String) {

  private val lock = new InterProcessReadWriteLock(curatorFramework, lockPath + "/" + this.getClass.getName)

  def readData(): Unit = {
    val readLock = lock.readLock()
    if (readLock.acquire(20, TimeUnit.SECONDS)) {
      println("我是来读取数据的")
      Thread.sleep(5000)
    }
    readLock.release()
  }

  def writeData(): Unit = {
    val writeLock = lock.writeLock()
    if (writeLock.acquire(20, TimeUnit.SECONDS)) {
      println("我是来写数据的")
      Thread.sleep(20000)
    }
    writeLock.release()
  }
}

object ZKDSharedReadWriteLock {
  def main(args: Array[String]): Unit = {
    val client = ZKClient.getZKClient("172.17.0.1:2181")
    val task = new ZKDSharedReadWriteLock(client, "/tmp")
    task.readData()
    task.writeData()
    task.readData()
  }
}
