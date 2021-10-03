
import sbt._

object Dependencies {
  val scalaVersion: String = "2.13.3"
  private val ScalaTestVersion: String = "3.2.9"
  private val jacksonVersion = "2.11.2"
  private val o3Version = "7.5.0-00"

  val jacksonModule = "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion
  val jacksonData = "com.fasterxml.jackson.dataformat" % "jackson-dataformat-xml" % jacksonVersion

  val velocity =  "org.apache.velocity" % "velocity" % "1.7"
  val scalaTest: ModuleID = "org.scalatest" %% "scalatest" % ScalaTestVersion % Test

  val o3Metadata = "biz.ideasoft.o3" % "o3-metadata" % o3Version
  val jLibUtil = "biz.ideasoft.jlib" % "util" % "2.0.28"
  //val castor = "castor" % "castor" % "0.9.5.2_is"

  val commonsLogging = "commons-logging" % "commons-logging" % "1.2"
  val commonsCodec = "commons-codec" % "commons-codec" % "1.4"
}