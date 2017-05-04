package com.young.distributed.core.storage

/**
  * Created by yangyong3 on 2017/5/4.
  */
class DStorageException(message:String,throwable: Throwable) extends Exception{

  def this(message:String) = this(message,new Exception)

  def this(throwable: Throwable) = this("",throwable)
}
