package com.young.distributed.core.remote.client.support.netty.handler

import com.young.distributed.core.remote.rpc.RpcResult
import io.netty.channel.{ChannelHandlerContext, SimpleChannelInboundHandler}

/**
  * Created by yangyong3 on 2017/6/2.
  */
class NettyClientResultHandler extends SimpleChannelInboundHandler[RpcResult]{
  override def channelRead0(ctx: ChannelHandlerContext, msg: RpcResult): Unit = {
    println(msg.result)
  }
}
