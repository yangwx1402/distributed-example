package com.young.distributed.core.reflect

/**
  * Created by yangyong on 17-5-7.
  */
class ReflectTest(message: String) {

  def print(): Unit = {
    println(message)
  }
}

object ReflectTest {
  def main(args: Array[String]): Unit = {
    val args = Array[AnyRef]("122")
    val targetArgsClass : Array[Class[_]] = args.map(arg=>arg.getClass)
    val instance: ReflectTest = ClassUtils.newInstance(classOf[ReflectTest], args,targetArgsClass)
    instance.print()
  }
}
