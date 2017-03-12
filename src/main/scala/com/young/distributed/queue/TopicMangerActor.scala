package com.young.distributed.queue

import akka.actor.{Actor, ActorRef, Props}

/**
  * Created by young.yang on 2017/3/12.
  */
class TopicMangerActor extends Actor with TopicManager {

  private val topics = Map[String, ActorRef]()

  override def receive: Receive = {
    //处理pub的时候
    case message: Message => {
      //已经有topic了
      if (topics.contains(message.topic.name)) {
        topics.get(message.topic.name).get ! message
      } else {
        //创建一个topicSender actor并将消息发送到topicSender进行转发
        val actor = context.actorOf(Props[TopicSender])
        actor ! message
        topics.+((message.topic.name, actor))
      }
    }
    //订阅某个topic，也就是说client会向TopicManager actor发送一个订阅事件,收到订阅后，应该
    case register: RegisterEvent => {
      if (topics.contains(register.topic.name)) {
        topics.get(register.topic.name).get ! RegisterProcess(sender(), register)
      } else {
        sender() ! "fail topic " + register.topic.name + " is not exist!"
      }
    }
  }
}
