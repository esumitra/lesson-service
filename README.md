## Lesson Service
### Overview
This project illustrates an **API-first** approach to developing microservices. The advantages of an API first approach are
1. Reduced dependencies by decoupling API specification from API implementation. Clients are dependent on the API specification only and not the implementation.
2. Generate different implementations of the specification
      - a mock implementation for testing
      - a server implementation which is the main service
      - a client implementation that other clients and microservices can use
3. Consumer Contract Based Testing of API specification
4. Early validation of solutions us that call multiple microservices API endpoints.

Two ways of implementing an API first approach for designing microservices are
- Configuration based: Swagger/OAS YAML files or similar
- Code based: The specification is implemented in code using Tapir or endpoint4s.

This project uses tapir (for typed API description) from https://github.com/softwaremill/tapir to implement an API first approach to developing microservices. 

### API Specification and Implementation
The API is specified at [Specification.scala](apispec/src/main/scala/com/example/lessonservice/apispec/Specification.scala) and is published to `"com.example.lesson-service" %% "api_spec" % <version>`. API clients need this dependency only.

The API specification is implemented using HTTP4S and ZIO at [HttpAdapter.scala](apiservice/src/main/scala/com/example/lessonservice/apiservice/HttpAdapter.scala) and is published to `"com.example.lesson-service" %% "api_service" % <version>`


The project requires Java 8 or Java 11, Scala 2.12.12 and sbt 1.5.2+ environment to run.

### Getting started
 Use the following commands to get started with your project

 - Compile: `sbt compile`
 - Run tests: `sbt test`
 - To install in local repo: `sbt publishLocal`

### Running the service
 - Start the server using `sbt run`
 - Open the documentation at http://localhost:8080/docs
 
### Static Analysis Tools

#### Scalafmt
To ensure clean code, run scalafmt periodically. The scalafmt configuration is defined at https://scalameta.org/scalafmt/docs/configuration.html

For source files,

`sbt scalafmt`

For test files.

`sbt test:scalafmt`

#### Scalafix
To ensure clean code, run scalafix periodically. The scalafix rules are listed at https://scalacenter.github.io/scalafix/docs/rules/overview.html

For source files,

`sbt "scalafix RemoveUnused"`

For test files.

`sbt "test:scalafix RemoveUnused"`

### License
Copyright 2021, Edward Sumitra

Licensed under the Apache License, Version 2.0.
