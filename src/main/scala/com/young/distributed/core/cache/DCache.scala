package com.young.distributed.core.cache

/**
  * Created by yangyong on 17-5-4.
  */
trait DCache[VALUE] {

  @throws[DCacheException]
  def set(key: String, value: VALUE)

  @throws[DCacheException]
  def get(key: String): VALUE

  @throws[DCacheException]
  def exist(key: String): Boolean
}
