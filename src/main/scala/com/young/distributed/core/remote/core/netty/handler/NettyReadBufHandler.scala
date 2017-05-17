package com.young.distributed.core.remote.core.netty.handler

import io.netty.buffer.ByteBuf
import io.netty.channel.{ChannelHandlerContext, SimpleChannelInboundHandler}

/**
  * Created by yangyong3 on 2017/5/17.
  */
class NettyReadBufHandler extends SimpleChannelInboundHandler[ByteBuf]{
  override def channelRead0(ctx: ChannelHandlerContext, msg: ByteBuf): Unit = {
    val size = msg.readableBytes()
    val bytes = new Array[Byte](size)
    msg.readBytes(bytes)
    ctx.fireChannelRead()
  }
}
