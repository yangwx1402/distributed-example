package com.young.distributed.core.register

/**
  * Created by yangyong on 17-5-14.
  */
trait ServiceRegister {

  def registerClient(service: Service)

  def getServices(consumer: Consumer):Array[Provider]
}
