package com.young.distributed.core.task.lock

/**
  * Created by yangyong on 17-5-8.
  */
class DLockTaskException(message:String,throwable: Throwable) extends Exception(message,throwable){

  def this(message:String) = this(message,null)

  def this(throwable: Throwable) = this("",throwable)

}
