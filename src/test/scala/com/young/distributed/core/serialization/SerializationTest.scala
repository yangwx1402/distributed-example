package com.young.distributed.core.serialization

import com.young.distributed.core.serialization.support.{JavaSerialization, JsonSerialization, KryoSerialization}

import scala.beans.BeanProperty

/**
  * Created by young.yang on 2017/3/12.
  */
class User extends Serializable {
  @BeanProperty
  var name: String = "";
  @BeanProperty
  var age: Int = 0;
}

case class Cost(name: String, length: Int, time: Long)

object SerializationTest {

  private def getUser(): User = {
    val user = new User()
    user.name = "杨勇"
    user.age = 32
    user
  }

  def compare(ds: Array[DSerializable[User, Array[Byte]]], user: User): Array[Cost] = ds.map(ele => {
    val start = System.currentTimeMillis()
    val bytes = ele.serialization(user)
    Cost(ele.getClass.getName, bytes.length, (System.currentTimeMillis() - start))
  })


  def sAndDe(ds: Array[DSerializable[User, Array[Byte]]], user: User) = ds.foreach(ele => {
    val bytes = ele.serialization(user)
    println(bytes)
    println(ele.deSerialization(bytes))
  })


  def main(args: Array[String]): Unit = {
    val user = getUser()
    val ds = Array[DSerializable[User, Array[Byte]]](new JsonSerialization[User](classOf[User]), new JavaSerialization[User], new KryoSerialization[User](classOf[User]))
    sAndDe(ds, user)
    compare(ds, user).foreach(println _)
  }
}
