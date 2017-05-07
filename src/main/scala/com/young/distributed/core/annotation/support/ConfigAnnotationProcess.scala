package com.young.distributed.core.annotation.support

import java.lang.annotation.Annotation
import java.util.Properties

import com.young.distributed.core.annotation.exception.ConfigException
import com.young.distributed.core.annotation.support.base.{AnnotationProcess, AnnotationProcessEntity}
import com.young.distributed.core.annotation.support.exception.AnnotationException
import com.young.distributed.core.config.{ConfigProperties, SystemEnv}
import com.young.distributed.core.reflect.AnnotationUtils
import com.young.distributed.core.utils.CodecUtils
import org.slf4j.LoggerFactory

/**
  * Created by yangyong on 17-5-6.
  */
class ConfigAnnotationProcess extends AnnotationProcess {

  private val processMap = new scala.collection.mutable.HashMap[String, String]

  private val log = LoggerFactory.getLogger(classOf[ConfigAnnotationProcess])

  private val PATH_FIELD = "value"
  @throws[AnnotationException]
  override def process[T](annotationProcessEntitys: AnnotationProcessEntity[T]*): Unit =
    annotationProcessEntitys.foreach(entity => {
      val annotation = entity.annotation
      val paths: Array[String] = AnnotationUtils.getAnnotationValue(annotation, PATH_FIELD)
      paths.foreach(path => {
        val md5 = CodecUtils.MD5(path)
        if (!processMap.contains(md5)) {
          var properties: Properties = null
          try {
            properties = ConfigProperties.readProperties(path)
          } catch {
            case e: Exception => throw new ConfigException("read properties path [" + path + "] error", e)
          }
          SystemEnv.addProperties(properties)
          processMap.+=((md5, path))
          log.info("process properties file [" + path + "] successed")
        } else {
          log.warn("properties file [" + path + "] has processed skipped")
        }
      })
    })
}



