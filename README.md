### ScalaTest Spring Integration (scalatest-spring)

Integrates ScalaTest with Spring to manage test context lifecycle using standard Spring annotations and a stackable Scala trait.

#### Getting Started

##### build.sbt

````scala
libraryDependencies ++= "com.github.scalaspring" %% "scalatest-spring" % "0.3.1"
````

##### Extend the TestContextManagement trait

* Extend the TestContextManagement trait in your test to automatically set up and tear down your Spring test context.
* Use the standard Spring ContextConfiguration annotation (see [Spring Testing Annotations](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/testing.html#integration-testing-annotations))
to identify the configuration to use.

````scala
@ContextConfiguration(classes = Array(classOf[SimpleConfiguration]))
class SimpleTestSpec extends FlatSpec with TestContextManagement with Matchers {

  // Use Spring compatible annotations (@Autowired or @Inject) to inject necessary dependencies
  // Note that Spring will inject val (read-only) fields, so this field will be non-null when tests run
  @Autowired val injected: Seq[String] = null

  "Dependency" should "be injected" in {
    // Some test that uses the injected dependency
    injected shouldEqual Seq("foo")
  }

}

@Configuration
class SimpleConfiguration {
    @Bean
    def someSeq: Seq[String] = Seq("foo")
}
````

#### Implementation Notes

* The `TestContextManagement` class is implemented as a stackable trait extending the `BeforeAndAfterAll` ScalaTest trait.
* Spring's `TestContextManager` class is used under the hood which reads the `@ContextConfiguration` attribute to set up the appropriate test context.
* If you need your own `beforeAll` or `afterAll` logic, be sure to call `super.beforeAll` and `super.afterAll` in your implementation to ensure that contexts are properly created and destroyed. See the project test source for an example.
