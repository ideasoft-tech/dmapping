
ThisBuild / organization := "uy.ideasoft"
ThisBuild / version      := "0.1-SNAPSHOT"

scalaVersion := "2.13.4"

val jacksonVersion = "2.11.2"

libraryDependencies +="com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion
libraryDependencies += "com.fasterxml.jackson.dataformat" % "jackson-dataformat-xml" % jacksonVersion


// publishTo := Some(MavenCache("local-maven", file("path/to/maven-repo/releases")))

publishTo := {
  val nexus = "http://nexus.i" +
    "deasoft.uy:8081/"
  if (isSnapshot.value)
    Some(("Ideasoft Nexus Snapshots" at nexus + "repository/snapshots").withAllowInsecureProtocol(true))
  else
    Some(("Ideasoft Nexus Releases"  at nexus + "repository/releases").withAllowInsecureProtocol(true))
}
publish / skip := false

resolvers += Resolver.mavenLocal

credentials ++= Seq(
  Credentials(Path.userHome / ".sbt" / ".credentials.releases"),
  Credentials(Path.userHome / ".sbt" / ".credentials.snapshots")
)

publishConfiguration := publishConfiguration.value.withOverwrite(true)
publishLocalConfiguration := publishLocalConfiguration.value.withOverwrite(true)