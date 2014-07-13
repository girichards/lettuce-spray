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

//  import HelloWorldServiceActor._
  import MyService._
  
  implicit val askTimeOut = Timeout(5000)
  implicit def executionContext: scala.concurrent.ExecutionContext = actorRefFactory.dispatcher

  def routes(actorRef: ActorRef): spray.routing.RequestContext => Unit =
    pathPrefix("api") {
      path("hello") {
        onComplete(actorRef ? ServiceMessage("hi there")) {
          case Success(reply: ServiceReply) => complete { reply.message }
          case Failure(f) => complete { "Failure: " + f }
        }
      }
    }

}
