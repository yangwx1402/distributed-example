package com.young.distributed.core.cache

/**
  * Created by yangyong3 on 2017/5/5.
  */
class DCacheException(message:String,throwable: Throwable) extends Exception(message,throwable){

  def this(message:String) = this(message,new Exception)

  def this(throwable: Throwable) = this("",throwable)
}
