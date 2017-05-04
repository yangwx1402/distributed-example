package com.young.distributed.core.storage.support

import com.young.distributed.core.storage.{DStorageException, DStorage}

/**
  * Created by yangyong3 on 2017/5/4.
  */
class ZKDStorage[KEY,VALUE] extends DStorage[KEY,VALUE]{
  @throws[DStorageException]
  override def write(key: KEY, value: VALUE): Unit = ???

  @throws[DStorageException]
  override def read(key: KEY): VALUE = ???

  @throws[DStorageException]
  override def del(key: KEY): Unit = ???
}
