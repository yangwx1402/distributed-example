package com.young.distributed.core.config

import java.io.{File, FileInputStream}
import java.util.Properties

/**
  * Created by yangyong on 17-5-6.
  */
object ConfigProperties {

  def readProperties(path: String):Properties={
    val bundler = new Properties
    if (path.startsWith("classpath"))
      bundler.load(new FileInputStream(this.getClass.getResource("/").getPath + File.separator + path.split(":")(1)))
    else
      bundler.load(new FileInputStream(path))
    bundler
  }
}
