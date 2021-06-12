package com.example.lessonservice.apispec
import sttp.tapir._
import sttp.tapir.json.circe._
import io.circe.generic.auto._

// only contains API specification - NO implementation
// equivalent to specifying in a config file like YAML
object Specification {

  // endpoint 1: score lesson quiz
  val scoreEndpoint = endpoint
    .post
    .in(auth.bearer[String])
    .in("api" / "scorelesson")
    .in(jsonBody[ScoreLessonRequestDTO])
    .out(jsonBody[ScoreResponseDTO])
    .errorOut(stringBody)

  // endpoint 2: list upcoming lessons
  val upcomingLessonsEndpoint = endpoint
    .get
    .in(auth.bearer[String])
    .in("api" / "upcominglessons" / path[String]("userId"))
    .out(jsonBody[UpcomingLessonsResponseDTO])
    .errorOut(stringBody)

  // YAML endpoint to generate Swagger documentation
  val yaml: String = {
    import sttp.tapir.docs.openapi._
    import sttp.tapir.openapi.circe.yaml._
    List(scoreEndpoint, upcomingLessonsEndpoint)
      .toOpenAPI("Lesson Service", "1.0")
      .toYaml
  }

}
