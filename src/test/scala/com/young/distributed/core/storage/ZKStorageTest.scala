package com.young.distributed.core.storage

import com.young.distributed.core.serialization.support.JsonSerialization
import com.young.distributed.core.storage.support.ZKDStorage
import com.young.distributed.core.zk.ZKClient

/**
  * Created by yangyong on 17-5-4.
  */
object ZKStorageTest {

  def main(args: Array[String]): Unit = {
    val client = ZKClient.getZKClient("172.17.0.1")
    val storage = new ZKDStorage[String](client,new JsonSerialization[String](classOf[String]))
    storage.write("test/1","1111")

  }
}
