package com.young.distributed.core.task.leader.support

import com.young.distributed.core.task.leader.{DLeaderTask, DLeaderTaskException}
import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.recipes.leader.{LeaderSelector, LeaderSelectorListenerAdapter}
import org.apache.curator.framework.state.ConnectionState

/**
  * Created by yangyong on 17-5-8.
  */
abstract class ZKLeaderTask(client: CuratorFramework,leaderPath:String) extends DLeaderTask {

  @throws[DLeaderTaskException]
  def task()

  @throws[DLeaderTaskException]
  override def runTask(): Unit = {
    val listener = new LeaderSelectorListenerAdapter() {
      override def takeLeadership(client: CuratorFramework): Unit = {
           task()
      }

      override def stateChanged(client: CuratorFramework, newState: ConnectionState): Unit = {

      }
    }
    client.getConnectionStateListenable().addListener(new ZKConnectionStateListener())
    val selector = new LeaderSelector(client,leaderPath+"/"+this.getClass.getName,listener)
    selector.autoRequeue();
    selector.start();
  }
}
