package com.young.distributed.core.serialization.support

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, ObjectInputStream, ObjectOutputStream}

import com.young.distributed.core.mq.MessageQueueException
import com.young.distributed.core.serialization.{DSerializable, SerializationException}

/**
  * Created by young.yang on 2017/3/12.
  */
class JavaSerialization[FROM <: Serializable] extends DSerializable[FROM, Array[Byte]] {
  @throws[SerializationException]
  override def serialization(from: FROM): Array[Byte] = {
    try {
      val bos = new ByteArrayOutputStream()
      val oos = new ObjectOutputStream(bos)
      oos.writeObject(from)
      oos.close()
      bos.toByteArray
    } catch {
      case e: Exception => throw new SerializationException(e)
    }
  }

  @throws[SerializationException]
  override def deSerialization(to: Array[Byte]): FROM = {
    try {
      val bis = new ByteArrayInputStream(to)
      val bos = new ObjectInputStream(bis)
      bos.readObject().asInstanceOf[FROM]
    } catch {
      case e: Exception => throw new SerializationException(e)
    }
  }
}
