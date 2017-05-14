package com.young.distributed.core.serialization.support

import com.young.distributed.core.serialization.{DSerializable, SerializationException}
import org.codehaus.jackson.map.ObjectMapper

/**
  * Created by young.yang on 2017/3/12.
  */
class JsonSerialization[FROM](clazz: Class[FROM]) extends DSerializable[FROM, Array[Byte]] {

  private val defaultEncode = "utf-8"
  private val jsonMapper = new ObjectMapper

  @throws[SerializationException]
  override def serialization(from: FROM): Array[Byte] = {
    try {
      if (from.isInstanceOf[String]) {
        from.toString.getBytes(defaultEncode)
      } else {
        jsonMapper.writeValueAsBytes(from)
      }
    } catch {
      case e: Exception => throw new SerializationException(e)
    }
  }

  @throws[SerializationException]
  override def deSerialization(to: Array[Byte]): FROM = {
    try {
      if (clazz == classOf[String]) {
        new String(to, defaultEncode).asInstanceOf[FROM]
      } else {
        jsonMapper.readValue(to, clazz)
      }
    } catch {
      case e: Exception => throw new SerializationException(e)
    }
  }
}
