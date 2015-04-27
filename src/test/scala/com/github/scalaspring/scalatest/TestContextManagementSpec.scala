package com.github.scalaspring.scalatest

import org.scalatest.{FlatSpec, Matchers}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ContextConfiguration


@ContextConfiguration(classes = Array(classOf[TestContextManagementSpec.Configuration]))
class TestContextManagementSpec extends FlatSpec with TestContextManagement with Matchers {

  @Autowired val applicationContext: ConfigurableApplicationContext = null
  @Autowired val someSeq: Seq[String] = null


  "Autowired properties" should "be non-null" in {
    applicationContext should not be null
    someSeq shouldEqual Seq("foo")
  }

  override def afterAll(): Unit = {
    super.afterAll()
    applicationContext.isActive shouldBe false
  }

}

object TestContextManagementSpec {

  @Configuration
  class Configuration {

    @Bean
    def someSeq: Seq[String] = Seq("foo")

  }

}