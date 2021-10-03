//import CommonDef.BuildEnv
//import com.typesafe.sbt.packager.docker.Cmd
import sbt.Keys.libraryDependencies
import sbt.io.Path.allSubpaths


ThisBuild / organization := "uy.ideasoft"
ThisBuild / version      := "0.1-SNAPSHOT"
ThisBuild / scalaVersion := Dependencies.scalaVersion

unmanagedBase := baseDirectory.value / "lib"

/**
 * Common Settings
 */
lazy val commonSettings = Seq(
  resolvers += ("Ideasoft Nexus Releases" at nexus + "repository/public").withAllowInsecureProtocol(true),
  resolvers += Resolver.mavenLocal,
  libraryDependencies ++= Seq(
    Dependencies.jacksonModule,
    Dependencies.jacksonData,
    Dependencies.scalaTest,
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
    libraryDependencies ++= Seq(
//      Dependencies.jLibUtil,
      Dependencies.velocity,
      Dependencies.commonsLogging,
      Dependencies.commonsCodec
     )
  )

// publishTo := Some(MavenCache("local-maven", file("path/to/maven-repo/releases")))
val nexus = "http://nexus.ideasoft.uy:8081/"
publishTo := {
  if (isSnapshot.value)
    Some(("Ideasoft Nexus Snapshots" at nexus + "repository/snapshots").withAllowInsecureProtocol(true))
  else
    Some(("Ideasoft Nexus Releases"  at nexus + "repository/releases").withAllowInsecureProtocol(true))
}
publish / skip := false

credentials ++= Seq(
  Credentials(Path.userHome / ".sbt" / ".is-credentials"),
)

publishConfiguration := publishConfiguration.value.withOverwrite(true)
publishLocalConfiguration := publishLocalConfiguration.value.withOverwrite(true)
