lazy val root = (project in file(".")).enablePlugins(ScalaJSPlugin,SbtWeb)
name := "easyReactWithScalaJSSample1"
version := "0.1-SNAPSHOT"
scalaVersion := "2.11.8"

scalaJSOutputMode := org.scalajs.core.tools.javascript.OutputMode.ECMAScript6
 
val immutable = "org.webjars.npm" % "immutable" % "3.8.1" / "3.8.1/dist/immutable.js"    minified "immutable.min.js"

val scalaXml = "org.scala-lang.modules" %% "scala-xml" % "1.0.3"
val scalaReflect = "org.scala-lang" % "scala-reflect" % "2.11.8"
val macroParadisePlugin = compilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
val reactjs = "org.webjars.npm" % "react" % "15.3.2" / "dist/react-with-addons.js" commonJSName "React"  minified "react-with-addons.min.js"  
val reactRedux = "org.webjars.npm" % "react-redux" % "4.4.5" / "react-redux.js" minified "react-redux.min.js" dependsOn ("dist/react-with-addons.js","redux.js","react-dom.js")
val redux = "org.webjars.npm" % "redux" % "3.6.0" / "redux.js" minified "redux.min.js"   
val reduxForm ="org.webjars.npm" % "redux-form" % "6.2.0" / "redux-form.js"   minified "redux-form.min.js"  dependsOn ("react-redux.js") // 6.0.5
val reactBootstrap ="org.webjars.npm" % "react-bootstrap" % "0.30.6" / "react-bootstrap.js" minified "react-bootstrap.min.js" dependsOn  ("dist/react-with-addons.js","react-dom.js")
//val reactRouterBootstrapxxxxx= "org.webjars.npm" % "react-router-bootstrap" % "0.23.1" / "ReactRouterBootstrap.js"  minified "ReactRouterBootstrap.min.js" dependsOn "ReactRouter.js"
// react-router" % "4.0.0-2" 2.8.1
val reactRouter= "org.webjars.npm" % "react-router" % "4.0.0-alpha.6" / "react-router.js"  minified "react-router.min.js" dependsOn ("dist/react-with-addons.js","react-dom.js")
//val reactRouter= "org.webjars.npm" % "react-router" % "4.0.0-alpha.6" / "ReactRouter.js"  minified "ReactRouter.min.js" dependsOn ("dist/react-with-addons.js","react-dom.js")
//val reactRouter= "org.webjars.npm" % "react-router" % "3.0.0" / "ReactRouter.js"  minified "ReactRouter.min.js" dependsOn ("dist/react-with-addons.js","react-dom.js")
val reactDom= "org.webjars.npm" % "react-dom" % "15.3.2" / "react-dom.js"         commonJSName "ReactDOM" minified "react-dom.min.js"   dependsOn "dist/react-with-addons.js"
val griddle="org.webjars.npm" % "griddle-react" % "0.7.0" / "Griddle.js"   

unmanagedBase <<= baseDirectory { base => base / "libs" }


libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.9.1",
    "com.lihaoyi" %%% "utest" % "0.3.0" % "test",
	scalaXml, 
	scalaReflect,
    macroParadisePlugin 
)
jsManifestFilter := {
  import org.scalajs.core.tools.jsdep.{JSDependencyManifest, JSDependency}

  (seq: Traversable[JSDependencyManifest]) => {
    seq map { manifest =>

      def isOkToInclude(jsDep: JSDependency): Boolean = {
        println(s"jsDep=>$jsDep")
        jsDep.resourceName != "immutable.js"  &&  jsDep.resourceName != "react-with-addons.js"  
      }

      new JSDependencyManifest(
        origin = manifest.origin,
        libDeps = manifest.libDeps filter isOkToInclude,
        requiresDOM = manifest.requiresDOM,
        compliantSemantics = manifest.compliantSemantics
      )
    }
  }
}

 
 // 
jsDependencies ++= Seq(
immutable,redux,reactjs,reactDom,reactRouter,reactBootstrap,reactRedux ,reduxForm,griddle    
)

 //persistLauncher in Compile := true

 mainClass in Compile := Some("examples.basicRouter.containers.Index")
logLevel := Level.Info
 
 
