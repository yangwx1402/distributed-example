package com.young.distributed.core.task.leader

/**
  * Created by yangyong on 17-5-8.
  */
class DLeaderTaskException(message: String, throwable: Throwable) extends Exception(message, throwable) {

  def this(message: String) = this(message, null)

  def this(throwable: Throwable) = this("", throwable)
}
