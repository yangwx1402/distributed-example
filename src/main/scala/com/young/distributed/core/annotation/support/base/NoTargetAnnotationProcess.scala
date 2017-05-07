package com.young.distributed.core.annotation.support.base

import java.lang.annotation.Annotation

/**
  * Created by yangyong on 17-5-7.
  */
trait NoTargetAnnotationProcess {

  def process(annotation: Annotation*): Unit
}
