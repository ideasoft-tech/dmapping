
ThisBuild / organization := "uy.ideasoft"
ThisBuild / version      := "0.1-SNAPSHOT"

name := "dmapping"

version := "0.1"

scalaVersion := "2.13.4"

val jacksonVersion = "2.11.2"

libraryDependencies +="com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion
libraryDependencies += "com.fasterxml.jackson.dataformat" % "jackson-dataformat-xml" % jacksonVersion


// publishTo := Some(MavenCache("local-maven", file("path/to/maven-repo/releases")))

publish / skip := false

resolvers += Resolver.mavenLocal

publishTo := Some(Resolver.mavenLocal)