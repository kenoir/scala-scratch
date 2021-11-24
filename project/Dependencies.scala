import sbt._

object Dependencies {
  object Versions {
    val scalaTestVersion = "3.2.9"
    val akkaVersion = "2.6.17"
    val grizzledVersion = "1.3.4"
  }

  import Versions._

  val appDeps = Seq(
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "org.clapper" %% "grizzled-slf4j" % grizzledVersion
  )

  val testDeps = Seq(
    "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
    "org.scalatest" %% "scalatest" % scalaTestVersion % Test
  )

  val appDependencies: Seq[ModuleID] = Seq(
    testDeps,
    appDeps
  ).flatten
}