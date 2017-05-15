package com.young.distributed.core.remote.client

/**
  * Created by yangyong on 17-5-15.
  */
class ClientException(message:String,throwable: Throwable) extends Exception(message,throwable){

  def this(message:String) = this(message,null)

  def this(throwable: Throwable) = this("",throwable)

}
