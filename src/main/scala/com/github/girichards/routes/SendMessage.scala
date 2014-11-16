package com.github.girichards.routes

import spray.httpx.marshalling.ToResponseMarshallable.isMarshallable
import spray.routing.Directive.pimpApply
import spray.routing._

class SendMessage extends Directives {

   def route(message : Option[String]): Route = {
     pathPrefix("send" / ".*".r) {
       param =>
         complete {
           s" Listing ${param} Items ${message}"
         }
     }
   }

 }
