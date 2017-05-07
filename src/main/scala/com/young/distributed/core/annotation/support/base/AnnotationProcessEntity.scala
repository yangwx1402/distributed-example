package com.young.distributed.core.annotation.support.base

import java.lang.annotation.Annotation

import scala.beans.BeanProperty

/**
  * Created by yangyong on 17-5-7.
  */
case class AnnotationProcessEntity[T](@BeanProperty target: Class[T], @BeanProperty annotation: Annotation,@BeanProperty targetArgs:Array[AnyRef] = Array()) {
  override def toString(): String = "target=[" + target.toString + "]annotation=[" + annotation.toString + "]"
}
