package com.young.distributed.core.remote.rpc

import scala.beans.BeanProperty


/**
  * Created by yangyong3 on 2017/5/18.
  */
case class RpcRequest(@BeanProperty interfaceClass:String,@BeanProperty method:String) extends Serializable
case class RpcResult(@BeanProperty result:AnyRef) extends Serializable
