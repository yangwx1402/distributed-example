package com.young.distributed.core.remote.server.support.netty.handler

import com.young.distributed.core.remote.core.netty.handler.{NettySerializationHandler, NettyRpcHandler, NettyUnSerializationHandler}
import com.young.distributed.core.remote.rpc.RpcRequest
import com.young.distributed.core.serialization.support.JavaSerialization
import io.netty.channel.{Channel, ChannelInitializer}

/**
  * Created by yangyong3 on 2017/5/16.
  */
class NettyServerInitHandler extends ChannelInitializer[Channel]{
  override def initChannel(ch: Channel): Unit = {
    val serialization = new JavaSerialization[RpcRequest]
     //首先反序列化
     ch.pipeline().addLast(new NettyUnSerializationHandler(serialization))
     //进行RPC调用
     ch.pipeline().addLast(new NettyRpcHandler())
     //将结果序列化后写回客户端
     ch.pipeline().addLast(new NettySerializationHandler(serialization))
  }
}
