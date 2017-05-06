package com.young.distributed.core.cache.support

import com.young.distributed.core.cache.{DCacheException, DCache}

/**
  * Created by yangyong3 on 2017/5/5.
  */
class ZKDCache[KEY, VALUE] extends DCache[KEY, VALUE]() {
  @throws[DCacheException]
  override def set(key: KEY, value: VALUE): Unit = {

  }

  @throws[DCacheException]
  override def exist(key: KEY): Boolean = ???

  @throws[DCacheException]
  override def get(key: KEY): VALUE = ???
}
