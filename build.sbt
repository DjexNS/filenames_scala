
name := "filenames_scala"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies ++= {
  val sparkVer = "2.2.1"
  val hadoopVer = "2.7.3"
  Seq(
    "org.apache.spark" %% "spark-core" % sparkVer % Provided,
    "org.apache.spark" %% "spark-sql" % sparkVer,
    "org.apache.hadoop" % "hadoop-aws" % hadoopVer,
    "commons-httpclient" % "commons-httpclient" % "3.1",
    "com.amazonaws" % "aws-java-sdk" % "1.7.4"
  )
}