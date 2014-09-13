package com.github.girichards

import org.specs2.mutable.Specification
import spray.http.StatusCode
import spray.http.StatusCodes.{ OK, Success }
import spray.testkit.Specs2RouteTest
import akka.testkit.TestKit
import akka.actor.ActorSystem
import akka.testkit.ImplicitSender
import org.specs2.mutable.After
//import spray.testkit.ScalatestRouteTest

abstract class AkkaTestKitBase(_system: ActorSystem) extends TestKit(_system)
  with After with ImplicitSender {
  def after = system.shutdown()
}

class NewHttpEndpointTestKitSpec extends Specification with Specs2RouteTest

  with NewHttpEndpointRoutes {

  sequential

  def actorRefFactory = system

  "Simplest Calls to the Endpoint22" should {

    "return a simple hello on /api" in new AkkaTestKitBase(system) {

      val backendRef = system.actorOf(BackendService.props)
      val serviceRef = system.actorOf(MyService.props(backendRef))

      Get("/api/hello") ~> routes(serviceRef) ~> check {
        responseAs[String] must contain("You said, 'hi there'")
      }
    }

  }

  "Simplest Calls to the Endpoint" should {

    "return a simple hello on /api" in {
      val backendRef = system.actorOf(BackendService.props)
      val serviceRef = system.actorOf(MyService.props(backendRef))
      Get("/api/hello") ~> routes(serviceRef) ~> check {
        responseAs[String] must contain("You said, 'hi there'")
      }
    }

  }
}
