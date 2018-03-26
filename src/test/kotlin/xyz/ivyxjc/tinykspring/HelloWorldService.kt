package xyz.ivyxjc.tinykspring

import org.junit.Assert


/**
 * @author yihua.huang@dianping.com
 */
class HelloWorldService {

    var text: String? = null
    var outputService: OutputService? = null

    fun helloWorld() {
        outputService?.output("Hello~!!")
    }
}


class OutputService {

    var helloWorldService: HelloWorldService? = null

    fun output(text: String) {
        Assert.assertNotNull(helloWorldService)
        println(text)
    }
}