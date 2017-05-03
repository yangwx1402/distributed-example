package com.young.distributed.core.serialization

import com.esotericsoftware.kryo.serializers.DefaultSerializers.KryoSerializableSerializer
import com.young.distributed.core.mq.support.ZKMessageQueue
import com.young.distributed.core.serialization.support.{JavaSerialization, JsonSerialization, KryoSerialization}
import com.young.distributed.core.zk.ZKClient

/**
  * Created by yangyong on 17-5-3.
  */
object ZKMessageQueueTest {

  def main(args: Array[String]): Unit = {
    val zkclient = ZKClient.getZKClient(zkServer = "172.17.0.1:2181")
    val mq = new ZKMessageQueue("/test",new JavaSerialization[User],zkclient)
//    val user = new User()
//    user.age = 33
//    user.name = "杨勇"
//    mq.add(user)

    println(mq.poll())
  }
}
