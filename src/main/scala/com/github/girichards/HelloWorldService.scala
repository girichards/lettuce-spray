package com.github.girichards

import akka.actor.{ Actor, ActorRef }
import akka.util.Timeout
import scala.concurrent.{ ExecutionContext, Future }
import scala.util.{ Failure, Success }
import spray.routing.directives.{ FutureDirectives, ParameterDirectives, PathDirectives, RouteDirectives, ParamDefMagnet }
import spray.routing.{ Directive, RouteConcatenation }
import spray.httpx.marshalling.ToResponseMarshallable.isMarshallable

import com.typesafe.scalalogging.slf4j.LazyLogging

class HelloWorldService(actor: ActorRef)(implicit executionContext: ExecutionContext) extends RouteConcatenation
  with PathDirectives with ParameterDirectives with RouteDirectives with FutureDirectives
  with LazyLogging {

  import HelloWorldServiceActor._
  import akka.pattern.ask
  import scala.concurrent.duration._

  implicit val timeout = Timeout(2.seconds)

  val route: spray.routing.RequestContext => Unit = {
    pathPrefix("x" / "y") {
      parameter('message.as[String]).as(Message) {
        payload =>
          path("simple") {
            complete { "" }
          } ~ path("withActor") {
            onComplete(actor ? Message) {
              case Success(value) => complete { "Succeded: " + value }
              case Failure(ex) => complete { "Failed: " + ex }
            }
          }
      }
    }
  }

}
