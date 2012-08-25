/* vim:set filetype=scala: */
name := "scooter"

version := "0.0.0"

organization := "org.scooter"

scalaVersion := "2.10.0-M7"

libraryDependencies ++= Seq(
  "io.netty" % "netty" % "4.0.0.Alpha2",
  "org.specs2" % "specs2_2.10.0-M7" % "1.12.1.1" % "test"
)

scalacOptions += "-feature"

scalacOptions += "-deprecation"
