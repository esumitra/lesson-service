import Dependencies._

ThisBuild / organization := "com.example.lesson-service"
ThisBuild / scalaVersion := "2.12.12"
ThisBuild / semanticdbEnabled := true // enable SemanticDB
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision // use Scalafix compatible version

lazy val root = project
  .in(file("."))
  .settings(
    name := "Lesson Service",
    commonSettings,
  )
  .enablePlugins(NoPublishPlugin) // disable publishing of the root project
  .aggregate(api_spec, api_service)

lazy val api_spec = project
    .in(file("apispec"))
    .settings(
      name := "api_spec",
      commonSettings,
      libraryDependencies ++= Dependencies.apispec
    )

lazy val api_service = project
    .in(file("apiservice"))
    .settings(
      name := "api_service",
      commonSettings,
      libraryDependencies ++= Dependencies.apiservice,
    )
    .dependsOn(api_spec % oneToOneClasspathDependencies)

lazy val oneToOneClasspathDependencies: String =
  "compile->compile;test->test"

lazy val compilerOptions = Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-language:postfixOps",
  "-Ywarn-unused-import",
  "-language:higherKinds", // HKT required for Monads and other HKT types
  "-Ypartial-unification" // PU required for better type inference
)

lazy val commonSettings = Seq(
  scalacOptions ++= compilerOptions,
  libraryDependencies ++= Dependencies.common,
)

addCommandAlias("run", "api_service/run")