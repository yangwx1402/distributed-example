package com.young.distributed.core.serialization

/**
  * Created by young.yang on 2017/3/12.
  * 序列化和反序列化接口
  */
trait DSerializable[FROM, TO] {
  @throws[SerializationException]
  def serialization(from: FROM): TO

  @throws[SerializationException]
  def deSerialization(to: TO): FROM
}
