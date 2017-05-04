package com.young.distributed.core.serialization.support

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.{Input, Output}
import com.esotericsoftware.kryo.serializers.JavaSerializer
import com.young.distributed.core.serialization.{DSerializable, SerializationException}

/**
  * Created by young.yang on 2017/3/12.
  */
class KryoSerialization[FROM<:Serializable](clazz:Class[FROM]) extends DSerializable[FROM,Array[Byte]]{
  private val kryo = new Kryo
  kryo.setReferences(false);
  kryo.register(clazz, new JavaSerializer());

  @throws[SerializationException]
  override def serialization(from: FROM): Array[Byte] = {
    try {
      val bos = new ByteArrayOutputStream()
      val output = new Output(bos)
      kryo.writeClassAndObject(output, from)
      output.flush()
      output.clear()
      bos.toByteArray
    }catch {
      case e:Exception => throw new SerializationException(e)
    }
  }

  @throws[SerializationException]
  override def deSerialization(to: Array[Byte]): FROM = {
    try {
      val input = new Input(new ByteArrayInputStream(to))
      kryo.readClassAndObject(input).asInstanceOf[FROM]
    }catch {
      case e:Exception => throw new SerializationException(e)
    }
  }
}
