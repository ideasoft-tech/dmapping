//import CommonDef.BuildEnv
//import com.typesafe.sbt.packager.docker.Cmd
import sbt.Keys.libraryDependencies
import sbt.io.Path.allSubpaths


ThisBuild / organization := "uy.ideasoft"
ThisBuild / version      := "0.1-SNAPSHOT"
ThisBuild / scalaVersion := Dependencies.scalaVersion



/**
 * Common Settings
 */
lazy val commonSettings = Seq(
  libraryDependencies ++= Seq(
    Dependencies.jacksonModule,
    Dependencies.jacksonData,
    Dependencies.scalaTest
  )
)

lazy val core = project.in(file("./modules/core"))
  .dependsOn()
  .aggregate()
  .settings(
    commonSettings
  )

lazy val o3Models = project.in(file("./modules/o3-models"))
  .dependsOn(core)
  .aggregate(core)
  .settings(
    commonSettings,
    libraryDependencies += Dependencies.o3Metadata
  )


// publishTo := Some(MavenCache("local-maven", file("path/to/maven-repo/releases")))

publishTo := {
  val nexus = "http://nexus.ideasoft.uy:8081/"
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
