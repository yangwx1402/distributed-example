package com.young.distributed.core.register

import scala.beans.BeanProperty

/**
  * Created by yangyong on 17-5-14.
  */

sealed trait Service

case class ServiceProvider(@BeanProperty name:String,@BeanProperty url:String,@BeanProperty version:String = "1.0") extends Service{
  override def toString: String = name+":"+url+":"+version

  def this() = this("","")
}

case class ServiceConsumer(@BeanProperty name:String,@BeanProperty service:String,@BeanProperty version:String = "1.0") extends Service
