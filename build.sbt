name := "weatherGraph"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)

libraryDependencies += "postgresql" % "postgresql" % "9.1-901-1.jdbc4"

libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.1.1"

libraryDependencies += "org.json" % "json" % "20141113"

play.Project.playJavaSettings
