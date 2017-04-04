name := """auth-service"""
organization := "com.thetiphub"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies += filters
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
libraryDependencies += "org.mongodb" %% "casbah" % "3.1.1"
libraryDependencies += "org.mindrot" % "jbcrypt" % "0.3m"
libraryDependencies += "com.github.salat" %% "salat" % "1.10.0"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.thetiphub.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.thetiphub.binders._"
