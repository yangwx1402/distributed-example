package com.young.distributed.core.remote.client.support.netty.handler

import com.young.distributed.core.remote.core.netty.handler.{NettyRpcHandler, NettySerializationHandler}
import com.young.distributed.core.remote.rpc.RpcRequest
import com.young.distributed.core.serialization.support.JavaSerialization
import io.netty.channel.{Channel, ChannelInitializer}

/**
  * Created by yangyong3 on 2017/5/18.
  */
class NettyClientInitHandler extends ChannelInitializer[Channel] {

  private val dSerialization = new JavaSerialization[RpcRequest]

  /**
    * rpcRequest--->序列化(out，client)--->反序列化(in，server)-->RPC调用(in，server)
    * rpcResult--->序列化(out，server)--->反序列化(in，client)
    *
    * @param ch
    */
  override def initChannel(ch: Channel): Unit = {
    ch.pipeline().addLast(new NettySerializationHandler[RpcRequest](dSerialization))
  }
}
