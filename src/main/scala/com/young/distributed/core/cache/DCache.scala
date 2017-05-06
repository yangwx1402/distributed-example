package com.young.distributed.core.cache

/**
  * Created by yangyong on 17-5-4.
  */
trait DCache[KEY, VALUE] {

  @throws[DCacheException]
  def set(key: KEY, value: VALUE)

  @throws[DCacheException]
  def get(key: KEY): VALUE

  @throws[DCacheException]
  def exist(key: KEY): Boolean
}
