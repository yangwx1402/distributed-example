name := "distributed-example"

version := "1.0"

scalaVersion := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers ++= Seq("ali" at "http://maven.aliyun.com/nexus/content/groups/public/", "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Maven Repository" at "http://repo1.maven.org/maven2/",
  "maven-restlet" at "http://maven.restlet.org",
  "osc" at "http://maven.oschina.net/content/groups/public/")

libraryDependencies ++= {
  val akka_version = "2.5.0"
  val jackson_version = "1.9.13"
  val kryo_version = "2.24.0"
  val curator_version = "3.3.0"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akka_version,
    "com.typesafe.akka" %% "akka-cluster" % akka_version,
    "org.codehaus.jackson" % "jackson-mapper-asl" % jackson_version,
    "org.codehaus.jackson" % "jackson-core-asl" % jackson_version,
    "com.esotericsoftware.kryo" % "kryo" % kryo_version,
    "org.apache.curator" % "curator-framework" % curator_version,
    "org.apache.curator" % "curator-recipes" % curator_version
  )
}
    