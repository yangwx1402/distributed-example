package com.young.distributed.core.annotation.support.base

import java.lang.annotation.Annotation

/**
  * Created by yangyong on 17-5-7.
  */
trait TargetAnnotationProcess {

  def process[T <: AnyRef](target: Class[T], annotation: Annotation): Unit

}
