name := "distributed-example"

version := "1.0"

scalaVersion := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers ++= Seq("ali" at "http://maven.aliyun.com/nexus/content/groups/public/")

libraryDependencies ++= {
  val akka_version = "2.5.0"
  val jackson_version = "1.9.13"
  val kryo_version = "2.24.0"
  val curator_version = "2.12.0"
  val slf4j_version = "1.7.21"
  val netty_version = "4.1.10.Final"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akka_version,
    "com.typesafe.akka" %% "akka-cluster" % akka_version,
    "org.codehaus.jackson" % "jackson-mapper-asl" % jackson_version,
    "org.codehaus.jackson" % "jackson-core-asl" % jackson_version,
    "com.esotericsoftware.kryo" % "kryo" % kryo_version,
    "org.apache.curator" % "curator-framework" % curator_version,
    "org.apache.curator" % "curator-recipes" % curator_version,
    "org.slf4j" % "slf4j-api" % slf4j_version,
    "org.slf4j" % "slf4j-simple" % slf4j_version,
    "io.netty" % "netty-all" % netty_version
  )
}
    