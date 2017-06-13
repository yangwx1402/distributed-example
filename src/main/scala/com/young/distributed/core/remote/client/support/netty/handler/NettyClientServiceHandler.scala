package com.young.distributed.core.remote.client.support.netty.handler

import com.young.distributed.core.remote.rpc.RpcRequest
import io.netty.channel.{ChannelHandlerContext, ChannelOutboundHandlerAdapter, ChannelPromise}

/**
  * Created by yangyong3 on 2017/6/2.
  */
class NettyClientServiceHandler extends ChannelOutboundHandlerAdapter{

  override def write(ctx: ChannelHandlerContext, msg: AnyRef, promise: ChannelPromise): Unit = {
    val request = RpcRequest("classname","method")
    ctx.writeAndFlush(request)
  }
}
