package com.young.distributed.core.remote.client.support.netty

import com.young.distributed.core.remote.client.{Client, ClientException}

/**
  * Created by yangyong on 17-5-15.
  */
class NettyClient extends Client {
  @throws[ClientException]
  override def connect(ip: String, port: Int): Unit = ???
}
