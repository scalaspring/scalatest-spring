# ScalaTest Spring Integration Library

A simple integration of ScalaTest with Spring to manage test context lifecycle.
Uses standard Spring annotations and a stackable Scala trait.

## Getting Started

### build.sbt

````scala
libraryDependencies ++= "com.github.lancearlaus" %% "scalatest-spring" % "0.1"
````

### Create a Configuration and extend the TestContextManagement trait

````scala
import org.scalatest.{FlatSpec, Matchers}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ContextConfiguration


@ContextConfiguration(classes = Array(classOf[SimpleConfiguration]))
class SimpleTestSpec extends FlatSpec with TestContextManagement with Matchers {

  // Use Spring @Autowired or Java @Inject annotation to inject necessary dependencies
  // Note that Spring will inject val (read-only) fields
  @Autowired val injected: Seq[String] = null

  "Dependency" should "be injected" in {
    // Test implementation that uses injected dependency
    injected shouldEqual Seq("foo")
  }

}

@Configuration
class SimpleConfiguration {

    @Bean
    def someSeq: Seq[String] = Seq("foo")

}
````

### Notes

* The `TestContextManagement` class is implemented as a stackable trait extending the `BeforeAndAfterAll` ScalaTest trait.
* Spring's `TestContextManager` class is used under the hood which reads the `@ContextConfiguration` attribute to set up the appropriate test context.
* If you need your own before/afterAll logic, be sure to call `super.beforeAll` and `super.afterAll` in your implementation to ensure that contexts are properly created and destroyed. See the project test source for an example.
