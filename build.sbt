/* vim:set filetype=scala: */
name := "scooter"

version := "0.0.0"

organization := "org.scooter"

scalaVersion := "2.10.0-M5"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "1.9-2.10.0-M5-B2" % "test",
  "org.specs2" %% "specs2" % "1.11" % "test"
)

scalacOptions += "-feature"
