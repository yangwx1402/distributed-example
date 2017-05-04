package com.young.distributed.core.storage

/**
  * Created by yangyong3 on 2017/5/4.
  */
trait DStorage[KEY, VALUE] {

  @throws[DStorageException]
  def write(key: KEY, value: VALUE): Unit
  @throws[DStorageException]
  def read(key: KEY):VALUE
  @throws[DStorageException]
  def del(key: KEY):Unit
}
