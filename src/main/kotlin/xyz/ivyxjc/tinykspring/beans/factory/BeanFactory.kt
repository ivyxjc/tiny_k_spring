package xyz.ivyxjc.tinykspring.beans.factory

import xyz.ivyxjc.tinykspring.beans.BeanDefinition
import xyz.ivyxjc.tinykspring.beans.BeanNotfoundException

interface BeanFactory {
    fun getBean(beanName: String): Any?
}

abstract class AbstractBeanFactory : BeanFactory {
    var beanMap = HashMap<String, BeanDefinition>()

    override fun getBean(beanName: String): Any {
        return beanMap[beanName]?.bean ?: throw BeanNotfoundException("Bean not found")
    }

    fun registerBeanDefinition(beanDefinition: BeanDefinition) {
        val bean = createBean(beanDefinition)
        beanDefinition.bean = bean
        beanMap[beanDefinition.beanName] = beanDefinition
    }

    abstract fun createBean(beanDefinition: BeanDefinition): Any
}


class AutowireCapableBeanFactory : AbstractBeanFactory() {

    override fun createBean(beanDefinition: BeanDefinition): Any {
        val bean = beanDefinition.beanClass.newInstance()
        applyPropertyValues(bean, beanDefinition)
        return bean
    }

    fun applyPropertyValues(bean: Any, beanDefinition: BeanDefinition) {
        for (item in beanDefinition.propertyValues.values) {
            val field = bean.javaClass.getDeclaredField(item.beanName)
            field.isAccessible = true
            field.set(bean, item.bean)
        }
    }

}