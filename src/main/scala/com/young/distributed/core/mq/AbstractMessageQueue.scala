package com.young.distributed.core.mq

import com.young.distributed.core.serialization.DSerializable

/**
  * Created by yangyong on 17-5-2.
  */
abstract class AbstractMessageQueue[MESSAGE, TO](dSerializable: DSerializable[MESSAGE, TO]) extends MessageQueue[MESSAGE] {

  @throws[MessageQueueException]
  protected def checkMessage(message: MESSAGE): Unit = {
    if(message == null){
      throw new MessageQueueException("message is null -["+message+"]")
    }
  }
}
