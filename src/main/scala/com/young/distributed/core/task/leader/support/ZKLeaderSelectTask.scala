package com.young.distributed.core.task.leader.support

import java.util.Scanner

import com.young.distributed.core.task.leader.{DLeaderSelectTask, DLeaderTaskException}
import com.young.distributed.core.zk.ZKConnectionStateListener
import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.recipes.leader.{LeaderSelector, LeaderSelectorListenerAdapter}
import org.apache.curator.framework.state.ConnectionState

/**
  * Created by yangyong on 17-5-8.
  * 该类用来实现进行分布式选举任务，任何时候都只有一个Master
  */
abstract class ZKLeaderSelectTask(client: CuratorFramework, leaderPath: String) extends DLeaderSelectTask {

  @throws[DLeaderTaskException]
  def task()

  @throws[DLeaderTaskException]
  override def runTask(is_stay: Boolean): Unit = {
    val listener = new LeaderSelectorListenerAdapter() {
      override def takeLeadership(client: CuratorFramework): Unit = {
        task()
      }

      override def stateChanged(client: CuratorFramework, newState: ConnectionState): Unit = {

      }
    }
    //client.getConnectionStateListenable().addListener(new ZKConnectionStateListener())
    val selector = new LeaderSelector(client, leaderPath + "/" + this.getClass.getName, listener)
    selector.autoRequeue()
    selector.start()
    if (is_stay)
      new Scanner(System.in).nextLine()
  }
}
