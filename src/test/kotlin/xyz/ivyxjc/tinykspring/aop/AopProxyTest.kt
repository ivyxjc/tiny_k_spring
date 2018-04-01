package xyz.ivyxjc.tinykspring.aop

import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import org.junit.Test
import xyz.ivyxjc.tinykspring.HelloWorldService
import xyz.ivyxjc.tinykspring.context.ClasspathXmlApplicationContext
import java.time.LocalTime

/**
 * @author Ivyxjc
 * @since 4/1/2018
 */

class AopProxyTest {


    @Test
    fun testAopProxy() {
        //建立Context，获取bean
        var context = ClasspathXmlApplicationContext("tinyioc.xml")
        var helloWorldService = context.getBean("helloWorldService") as HelloWorldService

        //设置被代理对象
        val advisedSupport = AdvisedSupport()
        advisedSupport.targetSource = TargetSource(helloWorldService, HelloWorldService::class.java)

        //设置拦截器
        advisedSupport.methodInterceptor = TimeInterceptor()


        val proxy = JdkDynamicAopProxy(advisedSupport)
        val proxyInstance = proxy.getProxy() as HelloWorldService

        proxyInstance.helloWorld()

    }

}


class TimeInterceptor : MethodInterceptor {
    override fun invoke(invocation: MethodInvocation): Any? {
        println("start at " + LocalTime.now())
        val proceed = invocation.proceed()
        println("end at " + LocalTime.now())
        return proceed
    }
}