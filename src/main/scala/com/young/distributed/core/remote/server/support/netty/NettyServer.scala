package com.young.distributed.core.remote.server.support.netty

import java.net.InetSocketAddress

import com.young.distributed.core.remote.server.support.netty.handler.NettyServerInitHandler
import com.young.distributed.core.remote.server.{Server, ServerException}
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel

/**
  * Created by yangyong on 17-5-15.
  */
class NettyServer extends Server {
  @throws[ServerException]
  override def start(port: Int): Unit = {
    val serverBoot = new ServerBootstrap()
    val connectionProcessGroup = new NioEventLoopGroup(10)
    val handlerProcessGroup = new NioEventLoopGroup(100)
    serverBoot.group(connectionProcessGroup, handlerProcessGroup).channel(classOf[NioServerSocketChannel]).childHandler(new NettyServerInitHandler)

    val SO_BACKLOG : ChannelOption[Int]  = ChannelOption.valueOf("SO_BACKLOG")
    serverBoot.childOption(SO_BACKLOG, 100)
    val future = serverBoot.bind(new InetSocketAddress("localhost", port)).sync()
    future.channel().closeFuture().sync()
  }
}

object NettyServer {

  private val server = new NettyServer

  def startServer(port: Int = 8080): Unit = {
    server.start(port)
  }

  def main(args: Array[String]) {
    NettyServer.startServer()
  }
}