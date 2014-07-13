package com.github.girichards

import akka.actor.{ Actor, Props }
import com.typesafe.scalalogging.slf4j.LazyLogging

object HelloWorldServiceActor {

  case class Message(message: String)
  case class Reply(message: String)

  def props: Props = Props(classOf[HelloWorldServiceActor])
}

class HelloWorldServiceActor extends Actor with LazyLogging {

  import HelloWorldServiceActor._

  def receive: Receive = {
    case Message(message) => sender ! Reply(s"You said, '${message}'")
  }
}
