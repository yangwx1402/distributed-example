package com.young.distributed.core.remote.core.netty.handler

import com.young.distributed.core.serialization.DSerializable
import io.netty.buffer.Unpooled
import io.netty.channel.{ChannelPromise, ChannelHandlerContext, ChannelOutboundHandlerAdapter}

/**
  * Created by yangyong3 on 2017/5/17.
  */
class NettySerializationHandler[T](dSerializable: DSerializable[T, Array[Byte]]) extends ChannelOutboundHandlerAdapter {

  override def write(ctx: ChannelHandlerContext, msg: AnyRef, promise: ChannelPromise): Unit = {
    val bytes = dSerializable.serialization(msg.asInstanceOf[T])
    val byteBuf = Unpooled.copiedBuffer(bytes)
    ctx.writeAndFlush(byteBuf)
  }
}
