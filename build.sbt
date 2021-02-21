import Dependencies._

name := "laplace"

version := "0.1"

scalaVersion := "2.13.4"


libraryDependencies ++= Seq(
  AkkaStream,
  AkkaStreamKafka,
  AkkaStreamTestKit
)