package com.young.distributed.core.mq

import java.util

/**
  * Created by yangyong on 17-5-2.
  */
trait MessageQueue[MESSAGE] {
  @throws[MessageQueueException]
  def add(message: MESSAGE)

  @throws[MessageQueueException]
  def poll(): Option[MESSAGE]

  @throws[MessageQueueException]
  def size(): Int

  @throws[MessageQueueException]
  def add(messages: util.Collection[MESSAGE])

  @throws[MessageQueueException]
  def poll(batch: Int): util.Collection[MESSAGE]
}
