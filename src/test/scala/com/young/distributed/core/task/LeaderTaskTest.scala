package com.young.distributed.core.task

import com.young.distributed.core.task.leader.DLeaderTaskException
import com.young.distributed.core.task.leader.support.ZKLeaderSelectTask
import com.young.distributed.core.zk.ZKClient
import org.apache.curator.framework.CuratorFramework

/**
  * Created by yangyong3 on 2017/5/9.
  */
class LeaderTaskTest(client: CuratorFramework, leaderPath: String) extends ZKLeaderSelectTask(client, leaderPath) {

  private var count = 0

  @throws[DLeaderTaskException]
  override def task(): Unit = {
    while(true) {
      count += 1
      println("count now is  " + count)
      Thread.sleep(10000)
    }
  }
}
object LeaderTaskTest{
  def main(args: Array[String]) {
    val client = ZKClient.getZKClient("172.17.0.1:2181")
    val task = new LeaderTaskTest(client,"/tmp")
    task.runTask(true)
  }
}
