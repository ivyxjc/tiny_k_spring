package xyz.ivyxjc.tinykspring.beans

import xyz.ivyxjc.tinykspring.beans.io.ResourceLoader


interface BeanDefinitionReader {
    fun loadBeanDefinitions(location: String)
}

abstract class AbstractBeanDefinitionReader(val resourceLoader: ResourceLoader) : BeanDefinitionReader {
    val registry = mutableMapOf<String, BeanDefinition>()

}