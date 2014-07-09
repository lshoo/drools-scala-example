import sbt._
import Keys._

name := "drools-scala-example"


version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies ++= {
	val droolsVersion = "5.6.0.Final"
  Seq(
    "org.drools" % "drools-compiler" % droolsVersion,
    "org.drools" % "drools-core" % droolsVersion,
    "org.drools" % "drools-jsr94" % droolsVersion,
    "org.drools" % "drools-decisiontables" % droolsVersion,
    "org.drools" % "knowledge-api" % droolsVersion,
    "com.sun.xml.bind" % "jaxb-xjc" % "2.2.4-1",
    "org.scalatest"       %   "scalatest_2.10" % "2.2.0" % "test"
  )
}

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-Ywarn-dead-code",
  "-language:_",
  "-target:jvm-1.7",
  "-encoding", "UTF-8"
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")


crossPaths := false

//conflictManager := ConflictManager.loose

