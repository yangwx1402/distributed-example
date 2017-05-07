package com.young.distributed.core.annotation.support

import java.lang.annotation.Annotation

/**
  * Created by yangyong on 17-5-7.
  */
trait AnnotationProcess {

  def process(annotation:Annotation*):Unit
}
