# ScalaTest Spring Integration Library

This project is a simple integration of ScalaTest with Spring to manage the lifecycle of test contexts.

# Getting Started

## build.sbt

````scala
libraryDependencies ++= "com.github.lancearlaus" %% "scalatest-spring" % "0.1"
````

## Create a Configuration and extend the TestContextManagement trait

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
    injected should not be null
  }

}

@Configuration
class SimpleConfiguration {

    @Bean
    def someSeq: Seq[String] = Seq("foo")

}


````