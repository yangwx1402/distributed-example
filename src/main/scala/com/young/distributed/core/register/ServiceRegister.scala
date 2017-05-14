package com.young.distributed.core.register

/**
  * Created by yangyong on 17-5-14.
  */
trait ServiceRegister {

  /**
    * 服务注册,这里应该将一个服务的接口和实现类都通过序列化,然后通过反射探测出接口中的所有方法,保存在注册中心
    * @param service
    */
  def registerClient(service: Service)

  /**
    * 服务调用的时候,首先在注册中心查询相应的接口,将该序列化信息发送到服务提供端,进行反射代理调用,最后将结果返回给调用端
    * 其实就是rpc,dubbo也是基于此实现
    * @param consumer
    * @return
    */
  def getServices(consumer: Consumer):Array[Provider]
}
