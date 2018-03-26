package xyz.ivyxjc.tinykspring.xml

import org.junit.Assert
import org.junit.Test
import xyz.ivyxjc.tinykspring.beans.io.ResourceLoader
import xyz.ivyxjc.tinykspring.beans.xml.XmlBeanDefinitionReader

class BeanDefinitionReaderTest {

    @Test
    fun test() {
        val beanDefinitionReader = XmlBeanDefinitionReader(ResourceLoader())
        beanDefinitionReader.loadBeanDefinitions("tinyioc.xml")
        val map = beanDefinitionReader.registry
        Assert.assertTrue(map.size > 0)
    }

}