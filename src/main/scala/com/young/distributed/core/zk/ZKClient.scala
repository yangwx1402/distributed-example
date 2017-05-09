package com.young.distributed.core.zk

import org.apache.curator.framework.{CuratorFramework, CuratorFrameworkFactory}
import org.apache.curator.retry.ExponentialBackoffRetry

/**
  * Created by yangyong on 17-5-2.
  */
class ZKClient(zkServer: String, retryInterval: Int, maxRetryTimes: Int) {

  private val lock = new Object

  private var instance: CuratorFramework = null

  private def getInstance(): CuratorFramework = {
    if (instance == null) {
      val retryPolicy = new ExponentialBackoffRetry(retryInterval, maxRetryTimes)
      instance = CuratorFrameworkFactory.newClient(zkServer, retryPolicy)
      instance.getConnectionStateListenable.addListener(new ZKConnectionStateListener)
      instance.start()
    }
    instance
  }
}

object ZKClient {

  private var zKClient: ZKClient = null

  def getZKClient(zkServer: String = "127.0.0.1:2181", retryInterval: Int = 1000, maxRetryTimes: Int = 10): CuratorFramework = {
    if (zKClient == null) {
      zKClient = new ZKClient(zkServer, retryInterval, maxRetryTimes)
    }
    zKClient.getInstance()
  }
}
