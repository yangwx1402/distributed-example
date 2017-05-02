package com.young.distributed.mq

import com.young.distributed.serialization.DSerializable

/**
  * Created by yangyong on 17-5-2.
  */
abstract class AbstractMessageQueue[MESSAGE](dSerializable: DSerializable[MESSAGE,String]) extends MessageQueue[MESSAGE]{

}
