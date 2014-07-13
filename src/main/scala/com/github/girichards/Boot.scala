package com.github.girichards

import akka.actor.{ ActorSystem, Props }
import akka.io.IO
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import com.typesafe.config.{ ConfigFactory, Config }
import com.typesafe.scalalogging.slf4j.LazyLogging

object Boot extends App with LazyLogging {

  val config = ConfigFactory.load()
  logger.info("Starting app...")

  implicit val system = ActorSystem("on-spray-can")

  val service = system.actorOf(HttpEndpoint.props, "demo-service")

  implicit val timeout = Timeout(5.seconds)
  IO(Http) ? Http.Bind(service, interface = "0.0.0.0", port = 8080)
}
