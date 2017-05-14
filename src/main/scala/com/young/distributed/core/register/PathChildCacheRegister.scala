package com.young.distributed.core.register

import java.util.concurrent.TimeUnit

import com.young.distributed.core.serialization.DSerializable
import com.young.distributed.core.serialization.support.JsonSerialization
import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.recipes.cache.PathChildrenCache
import org.apache.curator.framework.recipes.nodes.PersistentNode
import org.apache.zookeeper.CreateMode

import scala.collection.JavaConversions._
import scala.collection.mutable

/**
  * Created by yangyong on 17-5-14.
  */
class PathChildCacheRegister(curatorFramework: CuratorFramework, registerPath: String, dSerializable: DSerializable[ServiceProvider, Array[Byte]] = new JsonSerialization[ServiceProvider](classOf[ServiceProvider])) extends ServiceRegister {

  private val providerMapping = mutable.Map[String, PersistentNode]()

  private val consumerMapping = mutable.Map[String, PathChildrenCache]()

  /**
    * 1.服务提供者注册在这里:/register/provider/service1
    * 2.服务消费者监听1中的path
    */
  override def registerClient(service: Service): Unit = {
    if (service.isInstanceOf[ServiceProvider]) {
      val provider = service.asInstanceOf[ServiceProvider]
      val basePath = registerPath + "/provider/" + provider.name +"/node"
      val serviceNode = providerMapping.get(basePath).getOrElse({
        val cache = new PersistentNode(curatorFramework, CreateMode.EPHEMERAL_SEQUENTIAL, true, basePath, dSerializable.serialization(provider))
        cache.start()
        cache.waitForInitialCreate(1,TimeUnit.MINUTES)
        providerMapping.+=((provider.name, cache))
        cache
      })
      serviceNode.setData(dSerializable.serialization(provider))
      serviceNode.waitForInitialCreate(1,TimeUnit.MINUTES)
    } else {
      val consumer = service.asInstanceOf[ServiceConsumer]
      val basePath = registerPath + "/provider/" + consumer.service+""
      val nodeCache = consumerMapping.get(consumer.name).getOrElse({
        val cache = new PathChildrenCache(curatorFramework, basePath, true)
        cache.start()
        consumerMapping.+=((consumer.name, cache))
        cache
      })
    }
  }

  override def getServices(name: String, service: String): Array[Service] = {
    val cache = consumerMapping.get(name)
    if (cache.isEmpty)
      Array[Service]()
    else {
      val list = cache.get.getCurrentData
      val services = list.map(data => dSerializable.deSerialization(data.getData))
      services.toArray
    }
  }
}
