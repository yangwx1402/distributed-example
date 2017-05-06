package com.young.distributed.core.serialization

import com.young.distributed.core.annotation.Test

/**
  * Created by yangyong on 17-5-6.
  */
@Test("com.young.distributed")
class AnnotationTest {

}

object AnnotationTest{
  def main(args: Array[String]): Unit = {
    val clazz = classOf[AnnotationTest]
    val anns = clazz.getAnnotations
    anns.foreach(ann=>println(ann.getClass.getName))
    val ann = anns(0)
    val methods = ann.getClass.getMethod("value")
    println(methods.invoke(ann))
  }
}
