name := "search-code-test"

version := "1.0"

scalaVersion := "2.12.3"

trapExit := false

val circeVersion = "0.8.0"

libraryDependencies ++= Seq(
  "io.circe"                         %% "circe-core"              % circeVersion,
  "io.circe"                         %% "circe-generic"           % circeVersion,
  "io.circe"                         %% "circe-parser"            % circeVersion,
  "org.scalatest"                    %% "scalatest"               % "3.0.3"            % "test",
  "org.scalacheck"                   %% "scalacheck"              % "1.13.5"           % "test"
)


mainClass in(Compile, run) := Some("com.codetest.app.Main")