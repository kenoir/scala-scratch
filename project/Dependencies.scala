import sbt._

object Dependencies {
  object Versions {
    val scalaTestVersion = "3.2.9"
    val akkaVersion = "2.6.8"
    val akkaHttpVersion = "10.2.7"
    val grizzledVersion = "1.3.4"
  }

  import Versions._

  val appDeps = Seq(
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "org.clapper" %% "grizzled-slf4j" % grizzledVersion
  )

  val testDeps = Seq(
    "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
    "org.scalatest" %% "scalatest" % scalaTestVersion % Test
  )

  val appDependencies: Seq[ModuleID] = Seq(
    testDeps,
    appDeps
  ).flatten
}