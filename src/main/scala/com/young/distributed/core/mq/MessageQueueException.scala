package com.young.distributed.core.mq

/**
  * Created by yangyong on 17-5-2.
  */
class MessageQueueException(message: String, throwable: Throwable) extends Exception {
  def this(message: String) = {
    this(message, new Exception)
  }

  def this(throwable: Throwable) {
    this("", throwable)
  }
}
