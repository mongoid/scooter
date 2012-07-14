name := "scooter"

version := "0.0.0"

organization := "org.scooter"

scalaVersion := "2.10.0-M4"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "1.9-2.10.0-M4-B2" % "test"
)

scalacOptions += "-Xexperimental"

scalacOptions += "-feature"
