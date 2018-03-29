package xyz.ivyxjc.tinykspring.beans.factory

import xyz.ivyxjc.tinykspring.beans.BeanDefinition
import xyz.ivyxjc.tinykspring.beans.BeanDefinitionNotFoundException
import xyz.ivyxjc.tinykspring.beans.BeanReference

interface BeanFactory {
    fun getBean(beanName: String): Any?
}

abstract class AbstractBeanFactory : BeanFactory {
    private var beanDefinitionMap = HashMap<String, BeanDefinition>()
    private val beanDefinitionNames = mutableListOf<String>()

    override fun getBean(beanName: String): Any {
        val beanDefinition = beanDefinitionMap[beanName]
                ?: throw BeanDefinitionNotFoundException("Bean Definition not found")
        beanDefinition.bean ?: createBean(beanDefinition)
        return beanDefinition.bean!!
    }

    fun registerBeanDefinition(beanDefinition: BeanDefinition) {
        beanDefinitionMap[beanDefinition.beanName] = beanDefinition
        beanDefinitionNames.add(beanDefinition.beanName)
    }

    fun initialBeans() {
        for (item in beanDefinitionNames) {
            getBean(item)
        }
    }
    abstract fun createBean(beanDefinition: BeanDefinition): Any
}


class AutowireCapableBeanFactory : AbstractBeanFactory() {

    override fun createBean(beanDefinition: BeanDefinition): Any {
        val bean = beanDefinition.beanClass.newInstance()
        beanDefinition.bean = bean
        applyPropertyValues(bean, beanDefinition)
        return bean
    }

    private fun applyPropertyValues(bean: Any, beanDefinition: BeanDefinition) {
        for (item in beanDefinition.propertyValues.values) {
            val field = bean.javaClass.getDeclaredField(item.fieldName)
            field.isAccessible = true
            if (item.value is BeanReference) {
                field.set(bean, getBean(item.value.refName))
            } else {
                field.set(bean, item.value)
            }

        }
    }

}