import sbt.Keys._
import sbt._

object Dependencies {
  val AkkaVersion = "2.6.12"
  val AkkaStream: ModuleID = "com.typesafe.akka" %%  "akka-stream" % AkkaVersion
  val AkkaStreamTestKit: ModuleID = "com.typesafe.akka" %% "akka-stream-testkit" % AkkaVersion % Test
  val AkkaStreamKafka : ModuleID = "com.typesafe.akka" %% "akka-stream-kafka" % "2.0.7"
}
