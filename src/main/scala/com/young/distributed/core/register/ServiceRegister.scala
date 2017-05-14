package com.young.distributed.core.register

/**
  * Created by yangyong on 17-5-14.
  */
trait ServiceRegister {

  def registerClient(service: Service)

  def getServices(name:String,service:String):Array[Service]
}
