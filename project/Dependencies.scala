import sbt._

object Dependencies {
  object Versions {
    val scalaTestVersion = "3.2.9"
    val akkaVersion = "2.6.17"
    val akkaHttpVersion = "10.2.7"
    val akkaHttpJsonVersion = "1.38.2"
    val grizzledVersion = "1.3.4"
    val circeVersion = "0.14.1"
  }

  import Versions._

  val appDeps = Seq(
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "de.heikoseeberger" %% "akka-http-circe" % akkaHttpJsonVersion,
    "org.clapper" %% "grizzled-slf4j" % grizzledVersion
  )

  val circeDeps = Seq(
    "io.circe" %% "circe-core"  % circeVersion,
    "io.circe" %% "circe-generic"  % circeVersion,
    "io.circe" %% "circe-parser"  % circeVersion,
  )

  val testDeps = Seq(
    "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
    "org.scalatest" %% "scalatest" % scalaTestVersion % Test
  )

  val appDependencies: Seq[ModuleID] = Seq(
    testDeps,
    appDeps,
    circeDeps,
  ).flatten
}