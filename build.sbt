lazy val `scratch` = (project in file("."))
  .settings(
  name := "scratch",
  scalaVersion := "2.13.6",
  version := "0.1",
    // The contents of /project is magically imported here, see project/Dependencies.scala
  libraryDependencies ++= Dependencies.appDependencies
)