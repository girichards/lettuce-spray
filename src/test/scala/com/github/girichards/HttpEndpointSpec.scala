package com.github.girichards

import org.specs2.mutable.Specification
import spray.http.StatusCode
import spray.http.StatusCodes.{ OK, Success }
import spray.testkit.Specs2RouteTest

class HttpEndpointSpec extends Specification with Specs2RouteTest
  with HttpEndpointRoutes {

  def actorRefFactory = system

  "Simplest Calls to the Endpoint" should {
    
    "return a simple hello on /api" in {
      Get("/api") ~> routes ~> check {
        // "xxx" must have size (11)
        // status equals OK 
        responseAs[String] must contain("hello api")
      }
    }
    
  }

}