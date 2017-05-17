package com.young.distributed.core.utils

import java.nio.charset.Charset

import com.google.common.hash.Hashing

/**
  * Created by yangyong3 on 2017/5/17.
  */
object CodecUtils {

  def MD5(data:String):String = Hashing.md5().hashString(data,Charset.forName("utf-8")).toString

}
