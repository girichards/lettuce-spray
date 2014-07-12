organization  := "com.github.girichards"

name := "lettuce-spray"

version       := "0.1"

scalaVersion  := "2.10.3"

// scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-Ywarn-dead-code",
  "-language:_",
  "-target:jvm-1.7",
  "-encoding", "UTF-8"
)

org.scalastyle.sbt.ScalastylePlugin.Settings

resolvers ++= Seq(
  "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype releases" at "http://oss.sonatype.org/content/repositories/releases/",
  "spray" at "http://repo.spray.io/"
)

libraryDependencies ++= {
  val akkaV = "2.2.4"
  val sprayV = "1.2.1"
  val sprayJsonV = "1.2.6"
  val specs2V = "2.3.12"
  Seq(
    "io.spray"            %   "spray-can"     % sprayV,
    "io.spray"            %   "spray-routing" % sprayV,
    "io.spray"            %   "spray-testkit" % sprayV   % "test",
    "io.spray"            %   "spray-httpx"   % sprayV,
    "io.spray"            %%  "spray-json"    % sprayJsonV,
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV    % "test",
    "com.typesafe.play"   %%  "play-json"     % "2.2.0",
    "org.specs2"          %%  "specs2-core"   % specs2V % "test",
    "org.specs2"          %%  "specs2-junit"  % specs2V % "test",
    "junit"               % "junit"           % "4.11" % "test",
    "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2"
  )
}


// testOptions in Test += Tests.Argument("junitxml", "console")

Revolver.settings
