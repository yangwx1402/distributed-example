package com.young.distributed.core.annotation.exception

/**
  * Created by yangyong on 17-5-7.
  */
class ConfigException(message:String,throwable: Throwable) extends Exception(message,throwable){

  def this(message:String) = this(message,null)
}
