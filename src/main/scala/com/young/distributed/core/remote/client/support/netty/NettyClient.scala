package com.young.distributed.core.remote.client.support.netty

import com.young.distributed.core.remote.client.support.netty.handler.NettyClientInitHandler
import com.young.distributed.core.remote.client.{Client, ClientException}
import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel

/**
  * Created by yangyong on 17-5-15.
  */
class NettyClient extends Client {
  @throws[ClientException]
  override def connect(ip: String, port: Int): Unit = {
    val boot = new Bootstrap
    val group = new NioEventLoopGroup(2)
    val TCP_NODELAY :ChannelOption[Boolean] = ChannelOption.valueOf("TCP_NODELAY")
    boot.group(group).channel(classOf[NioSocketChannel]).option(TCP_NODELAY,true).handler(new NettyClientInitHandler)
    val future = boot.connect(ip,port).sync()
    future.channel().closeFuture().sync()
  }
}
object NettyClient{
  val client = new NettyClient

  def main(args: Array[String]) {
    client.connect("localhost",8080)
  }
}
