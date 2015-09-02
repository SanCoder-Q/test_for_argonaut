import sbt.Keys._

name := "test_for_argonaut"

version := "1.0"

scalaVersion := "2.11.4"

resolvers ++= Seq(
  "rea nexus release" at "http://nexus.delivery.realestate.com.au/nexus/content/repositories/releases",
  Resolver.url("scalasbt", new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns),
  "gphat" at "https://raw.github.com/gphat/mvn-repo/master/releases/",
  "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases")


libraryDependencies ++= Seq(
  "net.databinder"     %% "unfiltered-netty-server" % "0.8.4",
  "net.databinder"     %% "unfiltered-directives"   % "0.8.4",
  "net.databinder"     %% "unfiltered-filter"       % "0.8.4",
  "wabisabi"           %% "wabisabi"                % "2.1.3",
  "io.argonaut"        %% "argonaut"                % "6.1-M4",
  "io.argonaut"        %% "argonaut-unfiltered"     % "6.0.4",
  "org.specs2" %% "specs2" % "2.4.17" % "test"
)
    