package com.young.distributed.core.annotation

import com.young.distributed.core.annotation.support.ConfigAnnotationProcess
import com.young.distributed.core.config.SystemEnv
import com.young.distributed.core.reflect.AnnotationUtils

/**
  * Created by yangyong on 17-5-7.
  */
@Config(Array("classpath:default.properties","classpath:config.properties"))
class ConfigAnnotationTest {
}

object ConfigAnnotationTest{
  def main(args: Array[String]): Unit = {
    val configProess = new ConfigAnnotationProcess
    val annotation = AnnotationUtils.getAnnotation(classOf[ConfigAnnotationTest],classOf[Config])
    configProess.process(annotation)
    println(SystemEnv.get("name"))
  }
}
