package xyz.ivyxjc.tinykspring

import org.junit.Test
import xyz.ivyxjc.tinykspring.beans.BeanDefinition
import xyz.ivyxjc.tinykspring.beans.PropertyValue
import xyz.ivyxjc.tinykspring.beans.PropertyValues
import xyz.ivyxjc.tinykspring.beans.factory.AutowireCapableBeanFactory
import java.lang.Exception

/**
 * @author yihua.huang@dianping.com
 */
class BeanFactoryTest {

    @Test
    @Throws(Exception::class)
    fun test() {
        // 1.初始化beanfactory
        val beanFactory = AutowireCapableBeanFactory()

        // 2.bean定义
        val beanDefinition = BeanDefinition("helloWorldService", "xyz.ivyxjc.tinykspring.HelloWorldService")

        // 3.设置属性
        val propertyValues = PropertyValues()
        propertyValues.addPropertyValue(PropertyValue("text", "Hello World!"))
        beanDefinition.propertyValues = propertyValues

        // 4.生成bean
        beanFactory.registerBeanDefinition(beanDefinition)

        // 5.获取bean
        val helloWorldService = beanFactory.getBean("helloWorldService") as HelloWorldService
        helloWorldService.helloWorld()

    }
}
