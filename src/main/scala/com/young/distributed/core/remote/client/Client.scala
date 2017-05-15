package com.young.distributed.core.remote.client

/**
  * Created by yangyong on 17-5-15.
  */
trait Client {

  @throws[ClientException]
  def connect(ip:String,port:Int)

}
