package com.young.distributed.core.register

import java.util.Scanner

import com.young.distributed.core.zk.ZKClient

/**
  * Created by yangyong on 17-5-14.
  */
object ConsumerTest {

  def main(args: Array[String]): Unit = {
    val client = ZKClient.getZKClient("172.17.0.1:2181")
    val register = new PathChildCacheRegister(client,"/service")
    val service = "baidu"
    val name = "test"
    val consumer = new Consumer(name,service)
    register.registerClient(consumer)
    while(true) {
      register.getServices(consumer).foreach(println _)
      Thread.sleep(10000)
    }
  }
}
