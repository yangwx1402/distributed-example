package com.young.distributed.core.mq.support

import java.util

import com.young.distributed.core.mq.{AbstractMessageQueue, MessageQueueException}
import com.young.distributed.core.serialization.DSerializable
import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.imps.CuratorFrameworkState
import org.apache.curator.framework.recipes.queue.SimpleDistributedQueue
import org.slf4j.LoggerFactory

import scala.collection.JavaConversions._

/**
  * Created by yangyong on 17-5-2.
  */
class ZKMessageQueue[MESSAGE](queue: String, dSerializable: DSerializable[MESSAGE, Array[Byte]], client: CuratorFramework) extends AbstractMessageQueue[MESSAGE, Array[Byte]](dSerializable) {

  private val log = LoggerFactory.getLogger(this.getClass)

  if (client == null || client.getState != CuratorFrameworkState.STARTED)
    throw new MessageQueueException("zk client [" + client + "] is null or not started")
  private val simpleDistributedQueue = new SimpleDistributedQueue(client, queue)
  log.info("init a queue [" + queue + "]")

  @throws[MessageQueueException]
  override def add(message: MESSAGE): Unit = {
    simpleDistributedQueue.offer(dSerializable.serialization(message))
  }

  @throws[MessageQueueException]
  override def poll(): MESSAGE = {
    try {
      val data = simpleDistributedQueue.poll()
      dSerializable.deSerialization(data)
    } catch {
      case e => throw new MessageQueueException(e)
    }
  }

  @throws[MessageQueueException]
  override def size(): Int = {
    try {
      client.getChildren.forPath(queue).size()
    } catch {
      case e => throw new MessageQueueException(e)
    }
  }

  @throws[MessageQueueException]
  override def add(messages: util.Collection[MESSAGE]): Unit = {
    try {
      for (message <- messages)
        this.add(message)
    } catch {
      case e => throw new MessageQueueException(e)
    }
  }

  @throws[MessageQueueException]
  override def poll(batch: Int): util.Collection[MESSAGE] = {
     try{
        val list = new util.ArrayList[MESSAGE]()
        for(i<-0 until batch){
          val temp = poll()
          if(temp!=null)
            list.add(temp)
        }
        list
     }catch {
       case e => throw new MessageQueueException(e)
     }
  }
}
