
ThisBuild / organization := "uy.ideasoft"
ThisBuild / version      := "0.1-SNAPSHOT"

scalaVersion := "2.13.4"

val jacksonVersion = "2.11.2"

libraryDependencies +="com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion
libraryDependencies += "com.fasterxml.jackson.dataformat" % "jackson-dataformat-xml" % jacksonVersion


// publishTo := Some(MavenCache("local-maven", file("path/to/maven-repo/releases")))

publishTo := {
  val nexus = "http://nexus.ideasoft.uy:8081/"
  if (isSnapshot.value)
    Some(("Sonatype Nexus Repository Manager" at nexus + "repository/snapshots").withAllowInsecureProtocol(true))
  else
    Some(("Sonatype Nexus Repository Manager"  at nexus + "repository/releases").withAllowInsecureProtocol(true))
}
publish / skip := false

//resolvers += Resolver.mavenLocal
//
//publishTo := Some(Resolver.mavenLocal)
//publishTo := Some(Resolver.ivyStylePatterns)

//publishMavenStyle := false

credentials += Credentials(Path.userHome / ".sbt" / ".credentials")

publishConfiguration := publishConfiguration.value.withOverwrite(true)
publishLocalConfiguration := publishLocalConfiguration.value.withOverwrite(true)