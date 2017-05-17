package com.young.distributed.core.remote.core.netty.handler

import java.nio.charset.Charset

import io.netty.buffer.Unpooled
import io.netty.channel.{ChannelPromise, ChannelHandlerContext, ChannelOutboundHandlerAdapter}

/**
  * Created by yangyong3 on 2017/5/17.
  */
class NettyEntryHandler(entry: (String) => String) extends ChannelOutboundHandlerAdapter {

  override def write(ctx: ChannelHandlerContext, msg: AnyRef, promise: ChannelPromise): Unit = {
    val json = msg.asInstanceOf[String]
    val entryData = entry(json)
    val buf = Unpooled.copiedBuffer(entryData, Charset.forName("utf-8"))
    ctx.writeAndFlush(buf)
  }
}
