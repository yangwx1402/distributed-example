package com.young.distributed.core.task.barrier.support

import java.util.concurrent.TimeUnit

import com.young.distributed.core.task.barrier.DBarrier
import com.young.distributed.core.zk.ZKClient
import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.recipes.barriers.DistributedBarrier

/**
  * Created by yangyong3 on 2017/5/10.
  */
class ZKDBarrier(curatorFramework: CuratorFramework, barrierPath: String) extends DBarrier {

  def runTask(): Unit = {
    val barrier = new DistributedBarrier(curatorFramework, barrierPath + "/" + this.getClass.getName)
    barrier.setBarrier()
    Thread.sleep(10000)
    println("我到了")
    barrier.waitOnBarrier(1, TimeUnit.MINUTES)
    println("咱们都到了")
  }
}

object ZKDBarrier {
  def main(args: Array[String]) {
    val client = ZKClient.getZKClient("10.142.165.75:2181,10.142.165.75:2182,10.142.165.75:2183")
    val task = new ZKDBarrier(client, "/tmp")
    task.runTask()
  }
}
