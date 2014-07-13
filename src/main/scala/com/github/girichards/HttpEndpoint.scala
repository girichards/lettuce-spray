package com.github.girichards
import akka.actor.{ Actor, Props }
import spray.routing.HttpService
import spray.httpx.marshalling.ToResponseMarshallable.isMarshallable
import spray.routing.Directive.pimpApply

object HttpEndpoint {
  def props: Props = Props(classOf[HttpEndpoint])
}

class HttpEndpoint extends Actor with HttpEndpointRoutes {

  val actorRefFactory = context
  val receive = runRoute(routes)

}

trait HttpEndpointRoutes extends HttpService {

  def routes: spray.routing.RequestContext => Unit =
    pathPrefix("api") { complete { "hello api" } }

}
