package com.young.distributed.core.annotation.support

import com.young.distributed.core.annotation.support.base.{AnnotationProcess, AnnotationProcessEntity}
import com.young.distributed.core.annotation.support.exception.AnnotationException

/**
  * Created by yangyong on 17-5-6.
  */
class LeaderTaskAnnotationProcess extends AnnotationProcess{
  @throws[AnnotationException]
  override def process[T](annotationProcessEntitys: AnnotationProcessEntity[T]*): Unit = ???
}
