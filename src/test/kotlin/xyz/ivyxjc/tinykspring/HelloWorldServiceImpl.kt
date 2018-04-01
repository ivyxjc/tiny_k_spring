package xyz.ivyxjc.tinykspring

import org.junit.Assert


/**
 * @author yihua.huang@dianping.com
 */
interface HelloWorldService {
    fun helloWorld()
}

class HelloWorldServiceImpl : HelloWorldService {

    var text: String? = null
    var outputService: OutputService? = null

    override fun helloWorld() {
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