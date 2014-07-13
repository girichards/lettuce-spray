package com.github.girichards

import scala.util.Failure
import scala.util.Success

import com.typesafe.scalalogging.slf4j.LazyLogging

import BackendService.BackendMessage
import BackendService.BackendReply
import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.pattern.ask
import akka.util.Timeout

object MyService {

  case class ServiceMessage(message: String)
  case class ServiceReply(message: String)

  def props(backEnd: ActorRef): Props = Props(classOf[MyService], backEnd)
}

class MyService(backEnd: ActorRef) extends Actor with LazyLogging {

  import MyService._
  import BackendService._

  implicit val timeout: Timeout = Timeout(5000)
  implicit val executionContext = context.dispatcher

  def receive: Receive = {

    case ServiceMessage(message) => {
      val origin = sender
      backEnd ? BackendMessage(message) andThen {
        case Success(reply: BackendReply) => origin ! ServiceReply(reply.message)
        case Failure(f) =>
      }
    }

  }
}
