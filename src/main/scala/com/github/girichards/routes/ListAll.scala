package com.github.girichards.routes

import akka.actor.ActorRef
import spray.httpx.marshalling.ToResponseMarshallable.isMarshallable
import spray.routing.Directive.pimpApply
import spray.routing._

class ListAll extends Directives {

  def route: Route = {

    pathPrefix("list" / "all") {
      complete {
        "Listing All Items"
      }
    }
  }

}
