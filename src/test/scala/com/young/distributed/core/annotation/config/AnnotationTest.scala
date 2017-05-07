package com.young.distributed.core.annotation.config

import com.young.distributed.core.annotation.Test
import com.young.distributed.core.reflect.AnnotationUtils

/**
  * Created by yangyong on 17-5-6.
  */
@Test(Array("com.young.distributed", "com.young.2"))
class AnnotationTest {

}

object AnnotationTest{
  def main(args: Array[String]): Unit = {
    val clazz = classOf[AnnotationTest]
    val ann = AnnotationUtils.getAnnotation(clazz,classOf[Test])
    println(ann)
    println(AnnotationUtils.getAnnotationValue(ann,"value"))
  }
}
