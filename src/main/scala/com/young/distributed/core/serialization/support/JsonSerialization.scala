package com.young.distributed.core.serialization.support

import com.young.distributed.core.serialization.{DSerializable, SerializationException}
import org.codehaus.jackson.map.ObjectMapper

/**
  * Created by young.yang on 2017/3/12.
  */
class JsonSerialization[FROM](clazz:Class[FROM]) extends DSerializable[FROM,Array[Byte]]{

  private val jsonMapper = new ObjectMapper
  @throws[SerializationException]
  override def serialization(from: FROM): Array[Byte] = {
    try {
      jsonMapper.writeValueAsBytes(from)
    }catch {
      case e:Exception => throw new SerializationException(e)
    }
  }

  @throws[SerializationException]
  override def deSerialization(to: Array[Byte]): FROM = {
    try {
      jsonMapper.readValue(to, clazz)
    }catch {
      case e:Exception => throw new SerializationException(e)
    }
  }
}
