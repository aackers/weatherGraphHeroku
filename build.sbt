name := "weatherGraph"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)

libraryDependencies += "postgresql" % "postgresql" % "9.1-901-1.jdbc4"

libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.1.1"

libraryDependencies += "com.github.dvdme" % "ForecastIOLib" % "1.5.2"

play.Project.playJavaSettings
