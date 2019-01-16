name := "poebuddy-root"
organization in ThisBuild := "pro.poebuddy"
scalaVersion in ThisBuild := "2.12.8"


//////////////
// PROJECTS //
//////////////

lazy val global = project
  .in(file("."))
  .settings(settings)
  .aggregate(
    common,
    affixscraper
  )

lazy val common = project
  .settings(
    name := "common",
    settings,
    libraryDependencies ++= commonDependencies
  )

lazy val affixscraper = project
  .settings(
    name := "affixscraper",
    settings,
    libraryDependencies ++= commonDependencies ++ Seq(
      dependencies.akka,
      dependencies.akkaTestkit,
      dependencies.akkaHttp,
      dependencies.akkaHttpTestkit
    )
  )
  .dependsOn(common)

//////////////////
// DEPENDENCIES //
//////////////////


lazy val dependencies =
  new {
    val slf4jVersion = "1.7.25"
    val akkaVersion = "2.5.19"
    val akkaHttpVersion = "10.1.7"

    val slf4j = "org.slf4j" % "slf4j-log4j12" % slf4jVersion
    val akka = "com.typesafe.akka" %% "akka-actor" % akkaVersion
    val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test
    val akkaHttp = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
    val akkaHttpTestkit = "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test
  }

lazy val commonDependencies = Seq(
  dependencies.slf4j
)

//////////////
// SETTINGS //
//////////////

lazy val settings = commonSettings

lazy val compilerOptions = Seq(
  "-unchecked",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-deprecation",
  "-encoding",
  "utf8"
)

lazy val commonSettings = Seq(
  scalacOptions ++= compilerOptions,
  resolvers ++= Seq(
    "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository",
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots")
  )
)

lazy val assemblySettings = Seq(
  assemblyJarName in assembly := name.value + ".jar",
  assemblyMergeStrategy in assembly := {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case _                             => MergeStrategy.first
  }
)