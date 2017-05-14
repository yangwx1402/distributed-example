package com.young.distributed.core.register

import java.util.concurrent.TimeUnit

import com.esotericsoftware.kryo.serializers.DefaultSerializers.KryoSerializableSerializer
import com.young.distributed.core.serialization.{DSerializable, DSerializableFactory}
import com.young.distributed.core.serialization.support.{JavaSerialization, JsonSerialization, KryoSerialization}
import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.recipes.cache.PathChildrenCache
import org.apache.curator.framework.recipes.nodes.PersistentNode
import org.apache.zookeeper.CreateMode

import scala.collection.JavaConversions._
import scala.collection.mutable

/**
  * Created by yangyong on 17-5-14.
  */
class PathChildCacheRegister(curatorFramework: CuratorFramework, registerPath: String) extends ServiceRegister {

  private val providerMapping = mutable.Map[String, PersistentNode]()

  private val consumerMapping = mutable.Map[String, PathChildrenCache]()

  //private val providerSer : DSerializable[Provider,Array[Byte]] = DSerializableFactory.getInstance(classOf[JsonSerialization[Provider]].getName,Array[AnyRef](classOf[Provider]))
  private val providerSer : DSerializable[Provider,Array[Byte]] = DSerializableFactory.getInstance(classOf[KryoSerialization[Provider]].getName,Array[AnyRef](classOf[Provider]))

  //private val consumerSer : DSerializable[Consumer,Array[Byte]] = DSerializableFactory.getInstance(classOf[JsonSerialization[Consumer]].getName,Array[AnyRef](classOf[Consumer]))

  /**
    * 1.服务提供者注册在这里:/register/provider/service1
    * 2.服务消费者监听1中的path
    */
  override def registerClient(service: Service): Unit = {
    if (service.isInstanceOf[Provider]) {
      val provider = service.asInstanceOf[Provider]
      val basePath = registerPath + "/provider/" + provider.name + "/node"
      val serviceNode = providerMapping.get(basePath).getOrElse({
        val cache = new PersistentNode(curatorFramework, CreateMode.EPHEMERAL_SEQUENTIAL, true, basePath, providerSer.serialization(provider))
        cache.start()
        cache.waitForInitialCreate(1, TimeUnit.MINUTES)
        providerMapping.+=((service.name, cache))
        cache
      })
      serviceNode.setData(providerSer.serialization(provider))
      serviceNode.waitForInitialCreate(1, TimeUnit.MINUTES)
    } else if(service.isInstanceOf[Consumer]){
      val consumer = service.asInstanceOf[Consumer]
      val basePath = registerPath + "/provider/" + consumer.serviceName
      val nodeCache = consumerMapping.get(consumer.name).getOrElse({
        val cache = new PathChildrenCache(curatorFramework, basePath, true)
        cache.start()
        consumerMapping.+=((consumer.name, cache))
        cache
      })
    }
  }

  override def getServices(consumer: Consumer): Array[Provider] = {
    val cache = consumerMapping.get(consumer.name)
    if (cache.isEmpty)
      Array()
    else {
      val list = cache.get.getCurrentData
      val services = list.map(data => providerSer.deSerialization(data.getData))
      services.toArray
    }
  }
}
