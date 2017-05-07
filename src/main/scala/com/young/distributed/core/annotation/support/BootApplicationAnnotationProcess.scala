package com.young.distributed.core.annotation.support

import java.lang.annotation.Annotation

import com.young.distributed.core.annotation.support.base.TargetAnnotationProcess

/**
  * Created by yangyong on 17-5-6.
  */
class BootApplicationAnnotationProcess extends TargetAnnotationProcess{
  override def process[T<:AnyRef](target:Class[T],annotation:Annotation): Unit = ???
}
