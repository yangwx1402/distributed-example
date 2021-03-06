package com.young.distributed.core.register

import java.util.Scanner

import com.young.distributed.core.zk.ZKClient

/**
  * Created by yangyong on 17-5-14.
  */
object ProvicerTest {

  def main(args: Array[String]): Unit = {
    val client = ZKClient.getZKClient("172.17.0.1:2181")
    val register = new PathChildCacheRegister(client,"/service")
    val name = "baidu"
    register.registerClient(new Provider(name))
    new Scanner(System.in).nextLine()
  }
}
