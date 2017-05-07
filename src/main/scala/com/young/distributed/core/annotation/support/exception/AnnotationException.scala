package com.young.distributed.core.annotation.support.exception

/**
  * Created by yangyong on 17-5-7.
  */
class AnnotationException(message:String,throwable: Throwable) extends Exception(message,throwable){

  def this(message:String) = this(message,null)
}
