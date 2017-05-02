package com.young.distributed.mq.support

import java.util

import com.young.distributed.mq.{MessageQueue, MessageQueueException}

/**
  * Created by yangyong on 17-5-2.
  */
class ZKMessageQueue[MESSAGE] extends MessageQueue[MESSAGE]{
  @throws[MessageQueueException]
  override def add(message: MESSAGE): Unit = ???

  @throws[MessageQueueException]
  override def poll(): MESSAGE = ???

  @throws[MessageQueueException]
  override def size(): Int = ???

  @throws[MessageQueueException]
  override def add(messages: util.Collection[MESSAGE]): Unit = ???

  @throws[MessageQueueException]
  override def poll(batch: Int): util.Collection[MESSAGE] = ???
}
