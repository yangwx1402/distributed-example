package com.young.distributed.core.cache.support

import com.young.distributed.core.cache.{DCache, DCacheException}
import com.young.distributed.core.serialization.DSerializable
import com.young.distributed.core.storage.support.ZKDStorage
import org.apache.curator.framework.CuratorFramework

/**
  * Created by yangyong3 on 2017/5/5.
  */
class ZKDCache[VALUE](curatorFramework: CuratorFramework, dSerializable: DSerializable[VALUE,Array[Byte]]) extends DCache[VALUE]() {

  private val storage = new ZKDStorage[VALUE](curatorFramework,dSerializable)

  @throws[DCacheException]
  override def set(key: String, value: VALUE): Unit = {
    try {
      storage.write(key, value)
    }catch {
      case e:Exception => throw new DCacheException(e)
    }
  }

  @throws[DCacheException]
  override def exist(key: String): Boolean = {
    try {
      get(key) == null
    }catch {
      case e:Exception => throw new DCacheException(e)
    }
  }

  @throws[DCacheException]
  override def get(key: String): VALUE = {
    try {
      storage.read(key)
    }catch {
      case e:Exception => throw new DCacheException(e)
    }
  }
}
