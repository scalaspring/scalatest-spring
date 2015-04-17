# ScalaTest Spring Integration Library

This project is a simple integration of ScalaTest with Spring to manage the lifecycle of test contexts.

# Getting started

## build.sbt

````
libraryDependencies ++= "com.github.lancearlaus" %% "scalatest-spring" % "0.1"
````

## Extend the TestContextManagement trait

````
@ContextConfiguration(classes = Array(classOf[SomeConfiguration]))
class SomeTestSpec extends FlatSpec with TestContextManagement with Matchers {

  // Use standard Autowired Spring annotation to inject necessary dependencies
  // Note that Spring will inject val (read-only) fields
  @Autowired
  val someDependency: SomeClass = null

  "Some test" should "verify something" in {
    // Test implementation that uses injected dependency
    someDependency should not be null
  }

}
````