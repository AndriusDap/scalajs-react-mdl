enablePlugins(ScalaJSPlugin)

organization := "com.payalabs"
name := "scalajs-react-mdl"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.7"

jsDependencies += RuntimeDOM

libraryDependencies += "com.github.japgolly.scalajs-react" %%% "core" % "0.9.0"
jsDependencies += "org.webjars" % "react" % "0.13.3" / "react-with-addons.js" minified "react-with-addons.min.js" commonJSName "React"

lazy val root = project.in(file("."))
lazy val examples = project.settings(
  persistLauncher in Compile := true,
  skip in packageJSDependencies := false,
  scalaVersion := "2.11.7"
).dependsOn(root).enablePlugins(ScalaJSPlugin)

import com.typesafe.sbt.SbtSite.SiteKeys._

site.settings

makeSite <<= makeSite dependsOn (fullOptJS in (examples, Compile))

siteMappings ++= Seq(
  baseDirectory.value / "examples" / "target" / "scala-2.11" / "examples-opt.js" -> "examples-opt.js",
  baseDirectory.value / "examples" / "target" / "scala-2.11" / "examples-launcher.js" -> "examples-launcher.js",
  baseDirectory.value / "examples" / "target" / "scala-2.11" / "examples-jsdeps.js" -> "examples-jsdeps.js"
)