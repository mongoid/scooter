/* vim:set filetype=scala: */
name := "scooter"

version := "0.0.0"

organization := "org.scooter"

scalaVersion := "2.10.0-M5"

libraryDependencies ++= Seq(
  "io.netty" % "netty" % "3.5.3.Final",
  "org.specs2" %% "specs2" % "1.11" % "test"
)

scalacOptions += "-feature"

scalacOptions += "-deprecation"
