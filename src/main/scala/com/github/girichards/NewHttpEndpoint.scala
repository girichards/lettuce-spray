package com.github.girichards
import akka.actor.{ Actor, ActorRef, Props }
import akka.pattern.ask
import akka.util.Timeout
import spray.routing.HttpService
import spray.httpx.marshalling.ToResponseMarshallable.isMarshallable
import spray.routing.Directive.pimpApply
import scala.util.Success
import scala.util.Failure

object NewHttpEndpoint {
  def props(actorRef: ActorRef): Props = Props(classOf[NewHttpEndpoint], actorRef)
}

class NewHttpEndpoint(actorRef: ActorRef) extends Actor with NewHttpEndpointRoutes {

  val actorRefFactory = context
  val receive = runRoute(routes(actorRef))

}

trait NewHttpEndpointRoutes extends HttpService {

  import HelloWorldServiceActor._
  implicit val askTimeOut = Timeout(5000)
  implicit def executionContext = actorRefFactory.dispatcher

  def routes(actorRef: ActorRef) = pathPrefix("api") {
    path("hello") {
      onComplete(actorRef ? Message("hi there")) {
        case Success(reply: Reply) => complete { reply.message }
        case Failure(f) => complete { "Failure: " + f }
      }
    }
  }

}