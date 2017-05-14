package com.young.distributed.core.register

import scala.beans.BeanProperty

/**
  * Created by yangyong on 17-5-14.
  */

case class Service(@BeanProperty name: String, @BeanProperty version: String)

class Provider(override val name: String, override val version: String = "1.0") extends Service(name, version) {

  def this() = this("", "")
}

class Consumer(override val name: String, var serviceName: String, override val version: String = "1.0") extends Service(name, version)
