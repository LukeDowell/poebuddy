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
    affixscraper,
    `el-tradeapi-escuchador`
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
      dependencies.akkaHttpTestkit,
      dependencies.jsoup,
      dependencies.slick,
      dependencies.hickari,
      dependencies.h2
    )
  )
  .dependsOn(common)

lazy val `el-tradeapi-escuchador` = project
  .settings(
    name := "el-tradeapi-escuchador",
    settings,
    libraryDependencies ++= commonDependencies ++ Seq(
      dependencies.akka,
      dependencies.akkaTestkit,
      dependencies.akkaHttp,
      dependencies.akkaHttpTestkit,
    )
  )

//////////////////
// DEPENDENCIES //
//////////////////

lazy val dependencies =
  new {
    val logbackVersion = "1.2.3"
    val scalaLoggingVersion = "3.9.2"
    val akkaVersion = "2.5.19"
    val akkaHttpVersion = "10.1.7"
    val jsoupVersion = "1.11.3"
    val typesafeConfigVersion = "1.3.3"
    val scalaTestVersion = "3.0.5"
    val jacksonVersion = "2.9.8"
    val playJsonVersion = "2.6.10"
    val playJsonExtensionsVersion = "0.20.0"
    val slickVersion = "3.3.0"
    val h2Version = "1.4.192"

    val logback = "ch.qos.logback" % "logback-classic" % logbackVersion
    val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVersion
    val akka = "com.typesafe.akka" %% "akka-actor" % akkaVersion
    val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test
    val akkaHttp = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
    val akkaHttpTestkit = "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test
    val jsoup = "org.jsoup" % "jsoup" % jsoupVersion
    val typesafeConfig = "com.typesafe" % "config" % typesafeConfigVersion
    val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVersion % Test
    val jacksonDatabind = "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion
    val jacksonModuleScala = "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion
    val playJson = "com.typesafe.play" %% "play-json" % playJsonVersion
    val playJsonExtensions = "ai.x" %% "play-json-extensions" % playJsonExtensionsVersion
    val slick = "com.typesafe.slick" %% "slick" % slickVersion
    val hickari = "com.typesafe.slick" %% "slick-hikaricp" % slickVersion
    val h2 = "com.h2database" % "h2" % h2Version
  }

lazy val commonDependencies = Seq(
  dependencies.scalaLogging,
  dependencies.scalaTest,
  dependencies.typesafeConfig,
  dependencies.playJson,
  dependencies.playJsonExtensions
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