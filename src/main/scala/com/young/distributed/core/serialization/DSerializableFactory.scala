package com.young.distributed.core.serialization

import com.young.distributed.core.reflect.ClassUtils

/**
  * Created by yangyong on 17-5-14.
  */
object DSerializableFactory {

  def getInstance[FROM, TO](className: String, args: Array[AnyRef]): DSerializable[FROM, TO] = {
    if (args == null || args.isEmpty) {
      ClassUtils.newInstance(className)
    } else {
      val argsType: Array[Class[_]] = args.map(arg => arg.getClass)
      ClassUtils.newInstance(className, args, argsType)
    }
  }
}
