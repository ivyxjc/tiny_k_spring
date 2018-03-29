package xyz.ivyxjc.tinykspring.beans.context

import xyz.ivyxjc.tinykspring.beans.factory.AbstractBeanFactory
import xyz.ivyxjc.tinykspring.beans.factory.AutowireCapableBeanFactory
import xyz.ivyxjc.tinykspring.beans.factory.BeanFactory
import xyz.ivyxjc.tinykspring.beans.io.ResourceLoader
import xyz.ivyxjc.tinykspring.beans.xml.XmlBeanDefinitionReader


interface ApplicationContext : BeanFactory


abstract class AbstractApplicationContext(val beanFactory: AbstractBeanFactory) : ApplicationContext {

    abstract fun refresh()

    override fun getBean(beanName: String): Any? {
        return beanFactory.getBean(beanName)
    }
}

class ClasspathXmlApplicationContext : AbstractApplicationContext {
    val configLocation: String

    constructor(configLocation: String) : this(configLocation, AutowireCapableBeanFactory())

    constructor(configLocation: String, beanFactory: AbstractBeanFactory) : super(beanFactory) {
        this.configLocation = configLocation
        refresh()
    }

    override fun refresh() {
        var xmlBeanDefinitionReader = XmlBeanDefinitionReader(ResourceLoader())
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation)
        xmlBeanDefinitionReader.registry.forEach {
            beanFactory.registerBeanDefinition(it.value)
        }
    }
}