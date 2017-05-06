package com.young.distributed.core.serialization

/**
  * Created by young.yang on 2017/3/12.
  * 序列化和反序列化异常
  */
class SerializationException(message: String, throwable: Throwable) extends Exception(message,throwable) {

  def this(throwable: Throwable) {
    this("", throwable)
  }

  def this(message: String) {
    this(message, null)
  }
}
