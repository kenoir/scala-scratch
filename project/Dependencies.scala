import sbt._

object Dependencies {
  object Versions {
    val scalaTestVersion = "3.2.9"
    val awsVersion = "2.17.76"
  }

  import Versions._

  // This is a Java lib, look at https://github.com/aws/aws-sdk-java-v2#individual-services
  val awsDeps = Seq(
    "software.amazon.awssdk" % "auth" % awsVersion,
    "software.amazon.awssdk" % "s3" % awsVersion
  )

  val testDeps = Seq(
    "org.scalatest" %% "scalatest" % scalaTestVersion % Test
  )

  val appDependencies: Seq[ModuleID] = Seq(
    testDeps,
    awsDeps
  ).flatten
}