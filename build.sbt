lazy val `scratch` = (project in file("."))
  .settings(
  name := "scratch",
  scalaVersion := "2.13.6",
  version := "0.1",
    // The contents of /project is magically imported here, see project/Dependencies.scala
  libraryDependencies ++= Dependencies.appDependencies
)

// You can add dependencies like this, or as above.
//name := "scratch"
//version := "0.1"
//scalaVersion := "2.13.6"
//libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % "test"