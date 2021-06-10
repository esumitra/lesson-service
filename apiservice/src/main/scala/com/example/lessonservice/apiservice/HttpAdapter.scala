package com.example.lessonservice.apiservice

import org.http4s.HttpRoutes
import sttp.tapir.swagger.http4s.SwaggerHttp4s
import com.example.lessonservice.apispec.Specification
import com.example.lessonservice.apispec.{ScoreResponse, UpcomingLessonsResponseDTO, LessonDTO}
import zio._
import zio.interop.catz._
import cats.implicits._

object HttpAdapter {
  
  val upcomingLessons = UpcomingLessonsResponseDTO(
    "user1",
    List(
      LessonDTO("1", "Adding by tens"),
      LessonDTO("2", "Multiplying by five")
    )
  )

  val scoreRoute: HttpRoutes[Task] = Specification.scoreEndpoint.toZioRoutes {
    case (token, scoreRequest) => {
      if (token == "1234") {
        IO.succeed {
          ScoreResponse(scoreRequest.userId, scoreRequest.lessonId, 80)
        }
      } else {
        IO.fail("Invalid token")
      }
    }
  }

  val upcomingLessonsRoute: HttpRoutes[Task] = Specification.upcomingLessonsEndpoint.toZioRoutes {
    case (token, userId) => {
      if (token == "1234") {
        IO.succeed(upcomingLessons.copy(userId = userId))
      } else {
        IO.fail("Invalid token")
      }
    }
  }

  // OpenAPI docs implementation
  val swaggerRoute: HttpRoutes[Task] = new SwaggerHttp4s(Specification.yaml).routes[Task]

  // all routes in all
  val allRoutes = scoreRoute <+> upcomingLessonsRoute <+> swaggerRoute

}
