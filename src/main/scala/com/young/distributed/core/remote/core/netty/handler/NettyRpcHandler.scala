package com.young.distributed.core.remote.core.netty.handler

import com.young.distributed.core.remote.rpc.RpcResult
import io.netty.channel.{ChannelHandlerContext, SimpleChannelInboundHandler}

/**
  * Created by yangyong3 on 2017/5/18.
  */
class NettyRpcHandler extends SimpleChannelInboundHandler[RpcResult]{
  override def channelRead0(ctx: ChannelHandlerContext, msg: RpcResult): Unit = ???
}
