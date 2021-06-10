package com.example.lessonservice.apiservice

import com.typesafe.scalalogging.StrictLogging
import org.http4s.server.Router
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.syntax.kleisli._
import zio.{URIO, _}
import zio.interop.catz._
import zio.interop.catz.implicits._

object HttpServer extends App with StrictLogging {

  // main server
  override def run(args: List[String]): URIO[zio.ZEnv,zio.ExitCode] = {
    ZIO.runtime.flatMap { implicit runtime: Runtime[Any] =>
      BlazeServerBuilder[Task](runtime.platform.executor.asEC)
        .bindHttp(8080, "0.0.0.0")
        .withHttpApp(Router("/" -> (HttpAdapter.allRoutes)).orNotFound)
        .serve
        .compile
        .drain
        .fold(_ => ExitCode.failure, _ => ExitCode.success)
    }
  }

}
