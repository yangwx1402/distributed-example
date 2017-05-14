package com.young.distributed.core.serialization

import org.codehaus.jackson.map.ObjectMapper

/**
  * Created by yangyong on 17-5-14.
  */
class MyJsonSerialization[FROM](clazz:Class[FROM]) extends DSerializable[FROM,Array[Byte]]{

  private val mapper = new ObjectMapper

  private val encode = "utf-8"

  @throws[SerializationException]
  override def serialization(from: FROM): Array[Byte] = {
     mapper.writeValueAsString(from).getBytes(encode)
  }

  @throws[SerializationException]
  override def deSerialization(to: Array[Byte]): FROM = {
    mapper.readValue(to,clazz)
  }
}

object MyJsonSerialization{
  def main(args: Array[String]): Unit = {
    val ser = new MyJsonSerialization[User](classOf[User])
    val user = new User
    user.name = "yangyong"
    user.age = 32
    val data = ser.serialization(user)
    println(ser.deSerialization(data).name)
  }
}
