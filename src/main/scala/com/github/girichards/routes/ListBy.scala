package com.github.girichards.routes

import akka.actor.ActorRef
import spray.httpx.marshalling.ToResponseMarshallable.isMarshallable
import spray.routing.Directive.pimpApply
import spray.routing._

class ListBy extends Directives {

  def route: Route = {
    pathPrefix("list" / ".*".r) {
      param =>
        complete {
          s" Listing ${param} Items "
        }
    }
  }

}
