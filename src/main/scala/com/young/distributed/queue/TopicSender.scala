package com.young.distributed.queue

import akka.actor.{Actor, ActorRef}

/**
  * Created by young.yang on 2017/3/12.
  */
class TopicSender extends Actor {

  private val clients = Set[ActorRef]()

  override def receive: Receive = {
    //收到消息
    case message: Message => {
      clients.map(actor => actor ! message)
    }
    case process: RegisterProcess => {
      clients.+(process.client)
    }
  }
}
