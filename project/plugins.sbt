logLevel := Level.Warn

resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

// Generally we add build step with plugins like this one:
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.2")

// You can add "auto-plugins", see this example:
// https://github.com/wellcomecollection/catalogue-pipeline/blob/main/project/BuildEnv.scala#L8
