package com.young.distributed.core.task.leader.support

import java.util.Scanner

import com.young.distributed.core.task.leader.DLeaderLatchTask
import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.recipes.leader.{LeaderLatchListener, LeaderLatch}

/**
  * Created by yangyong3 on 2017/5/9.
  */
abstract class ZKDLeaderLatchTask(curatorFramework: CuratorFramework, leaderPath: String) extends DLeaderLatchTask {
  def runTask(is_stay:Boolean): Unit = {
    val listener = new LeaderLatchListener {

      override def isLeader: Unit = {
        leaderTask()
      }

      override def notLeader(): Unit = {
        notLeaderTask()
      }
    }
    val leaderLatch = new LeaderLatch(curatorFramework, leaderPath+"/"+this.getClass.getName)
    leaderLatch.addListener(listener)
    leaderLatch.start
    if(is_stay)
      new Scanner(System.in).nextLine()
  }

  def leaderTask()

  def notLeaderTask()
}
