package com.young.distributed.core.task.leader.support

import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.state.{ConnectionState, ConnectionStateListener}

/**
  * Created by yangyong on 17-5-8.
  */
class ZKConnectionStateListener extends ConnectionStateListener{
  override def stateChanged(client: CuratorFramework, newState: ConnectionState): Unit = ???
}
