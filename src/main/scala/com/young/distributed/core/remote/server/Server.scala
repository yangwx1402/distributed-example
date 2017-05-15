package com.young.distributed.core.remote.server

/**
  * Created by yangyong on 17-5-15.
  */
trait Server {

  @throws[ServerException]
  def start(port:Int)
}
