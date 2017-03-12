package com.young.distributed.queue

import akka.actor.ActorRef

/**
  * Created by young.yang on 2017/3/12.
  */
case class Topic(name:String)

case class Message(topic:Topic,message:String)

case class RegisterEvent(topic:Topic)

case class RegisterProcess(client:ActorRef,registerEvent: RegisterEvent)


