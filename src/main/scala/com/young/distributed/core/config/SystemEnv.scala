package com.young.distributed.core.config

import java.util
import java.util.Properties

import com.young.distributed.core.annotation.exception.ConfigException

import collection.JavaConversions._

/**
  * Created by yangyong on 17-5-7.
  */
object SystemEnv {
  private val env = new util.Hashtable[String, AnyRef]()

  @throws[ConfigException]
  def addProperties(properties: Properties): Unit = {
    val names = properties.stringPropertyNames()
    for (name <- names) {
      if (env.containsKey(name)) {
        throw new ConfigException("SystemEnv has key [" + name + "],config key isn't repeat")
      }
      env.put(name, properties.get(name))
    }
  }

  def getProperty(key: String) = {
    val value = get(key)
    if (value != null)
      value.toString
    else
      null
  }

  def get(key: String): AnyRef = env.get(key)

}
