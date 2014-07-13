package com.github.girichards

import akka.actor.{ Actor, Props }
import com.typesafe.scalalogging.slf4j.LazyLogging

object BackendService {

  case class BackendMessage(message: String)
  case class BackendReply(message: String)

  def props: Props = Props(classOf[BackendService])
}

class BackendService extends Actor with LazyLogging {

  import BackendService._

  def receive: Receive = {
    case BackendMessage(message) => sender ! BackendReply(s"Backend is replying to your message. You said, '${message}'")
  }
}
