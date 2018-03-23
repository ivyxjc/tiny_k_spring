package xyz.ivyxjc.tinykspring


/**
 * @author yihua.huang@dianping.com
 */
class HelloWorldService {

    private var text: String? = null

    fun helloWorld() {
        println(text)
    }

    fun setText(text: String) {
        this.text = text
    }
}
