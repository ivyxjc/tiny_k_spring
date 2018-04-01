package xyz.ivyxjc.tinykspring.context

import org.junit.Test
import xyz.ivyxjc.tinykspring.HelloWorldService


class ApplicationContextTest {

    @Test
    fun test() {
        var context = ClasspathXmlApplicationContext("tinyioc.xml")
        var helloWorldService = context.getBean("helloWorldService") as HelloWorldService
        helloWorldService.helloWorld()
    }

}