
lazy val ScalaTestSpring = (project in file(".")).
  settings(net.virtualvoid.sbt.graph.Plugin.graphSettings: _*).
  settings(
    organization        := "com.github.scalaspring",
    name                := "scalatest-spring",
    description         := "Integrates ScalaTest with Spring to manage test context lifecycle using standard Spring annotations and a stackable Scala trait",
    scalaVersion        := "2.11.8",
    crossScalaVersions  := Seq("2.10.6"),
    javacOptions        := Seq("-source", "1.7", "-target", "1.7"),
    scalacOptions       ++= Seq("-feature", "-deprecation"),
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "2.2+",
      "org.springframework" % "spring-context" % "4.+",
      "org.springframework" % "spring-test" % "4.+"
    ),
    // Publishing settings
    publishMavenStyle       := true,
    publishArtifact in Test := false,
    pomIncludeRepository := { _ => false },
    publishTo := {
      val nexus = "https://oss.sonatype.org/"
      if (isSnapshot.value)
        Some("snapshots" at nexus + "content/repositories/snapshots")
      else
        Some("releases"  at nexus + "service/local/staging/deploy/maven2")
    },
    pomExtra :=
      <url>http://github.com/scalaspring/scalatest-spring</url>
      <licenses>
        <license>
          <name>Apache License, Version 2.0</name>
          <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
          <distribution>repo</distribution>
        </license>
      </licenses>
      <scm>
        <url>git@github.com:scalaspring/scalatest-spring.git</url>
        <connection>scm:git:git@github.com:scalaspring/scalatest-spring.git</connection>
      </scm>
      <developers>
        <developer>
          <id>lancearlaus</id>
          <name>Lance Arlaus</name>
          <url>http://lancearlaus.github.com</url>
        </developer>
      </developers>
  )