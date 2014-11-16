package com.github.girichards

import akka.actor.{Actor, Props}
import com.github.girichards.routes.{SendMessage, ListBy, ListAll}
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

  val listAll = new ListAll
  val listBy = new ListBy
  val sendMessage = new SendMessage

  def routes = pathPrefix("api") {
      listAll.route ~
      listBy.route ~
      parameters('message.?) { message =>
        sendMessage.route(message)
      }
  }

}