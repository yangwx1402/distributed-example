package com.young.distributed.core.task

import com.young.distributed.core.task.leader.support.ZKDLeaderLatchTask
import com.young.distributed.core.zk.ZKClient
import org.apache.curator.framework.CuratorFramework

/**
  * Created by yangyong3 on 2017/5/9.
  */
class LeaderLatchTaskTest(curatorFramework: CuratorFramework,path:String) extends ZKDLeaderLatchTask(curatorFramework,path){
  override def leaderTask(): Unit = {
    println("i am leader")
  }

  override def notLeaderTask(): Unit = {
    println("i am not leader")
  }
}
object LeaderLatchTaskTest{
  def main(args: Array[String]) {
    val client = ZKClient.getZKClient("10.142.165.75:2181,10.142.165.75:2182,10.142.165.75:2183")
    val task = new LeaderLatchTaskTest(client,"/tmp")
    task.runTask(true)
  }
}
