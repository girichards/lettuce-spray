package com.github.girichards

import org.specs2.mutable.Specification
import spray.http.StatusCode
import spray.http.StatusCodes.{ OK, Success }
import spray.testkit.Specs2RouteTest

class NewHttpEndpointSpec extends Specification with Specs2RouteTest
  with NewHttpEndpointRoutes {

  def actorRefFactory = system

  "Simplest Calls to the Endpoint" should {
    
    val helloActorRef = system.actorOf(HelloWorldServiceActor.props)
    
    "return a simple hello on /api" in {
      Get("/api/hello") ~> routes(helloActorRef) ~> check {
        // "xxx" must have size (11)
        // status equals OK 
        responseAs[String] must contain("You said, 'hi there'")
      }
    }
    
  }

}