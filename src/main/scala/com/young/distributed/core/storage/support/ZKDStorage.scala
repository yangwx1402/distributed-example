package com.young.distributed.core.storage.support

import com.young.distributed.core.serialization.DSerializable
import com.young.distributed.core.storage.{DStorage, DStorageException}
import org.apache.curator.framework.CuratorFramework

/**
  * Created by yangyong3 on 2017/5/4.
  */
class ZKDStorage[VALUE](client: CuratorFramework, dSerializable: DSerializable[VALUE, Array[Byte]]) extends DStorage[String, VALUE] {

  @throws[DStorageException]
  private def checkPath(key: String): Unit = {
    if (key == null || key.isEmpty) {
      throw new DStorageException(" path is  [" + key + "]")
    }
    if (!key.startsWith("/")) {
      throw new DStorageException("path must be start with / ,your path is [" + key + "]")
    }
  }

  @throws[DStorageException]
  override def write(key: String, value: VALUE): Unit = {
    checkPath(key)
    try {
      if (client.checkExists().forPath(key) == null) {
        client.create().creatingParentsIfNeeded().forPath(key, dSerializable.serialization(value))
      } else {
        client.setData().forPath(key, dSerializable.serialization(value))
      }
    } catch {
      case e: Exception => throw new DStorageException("write path [" + key + "],data is [" + value + "]", e)
    }
  }

  @throws[DStorageException]
  override def read(key: String): VALUE = {
    checkPath(key)
    try {
      val data = client.getData.forPath(key)
      dSerializable.deSerialization(data)
    } catch {
      case e: Exception => throw new DStorageException("read path [" + key + "]", e)
    }
  }

  @throws[DStorageException]
  override def del(key: String): Unit = {
    checkPath(key)
    try {
      client.delete().forPath(key)
    } catch {
      case e: Exception => throw new DStorageException("delete path [" + key + "]", e)
    }
  }
}
