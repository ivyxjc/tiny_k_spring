package xyz.ivyxjc.tinykspring.beans.xml

import org.w3c.dom.Document
import org.w3c.dom.Element
import xyz.ivyxjc.tinykspring.beans.AbstractBeanDefinitionReader
import xyz.ivyxjc.tinykspring.beans.BeanDefinition
import xyz.ivyxjc.tinykspring.beans.PropertyValue
import xyz.ivyxjc.tinykspring.beans.io.ResourceLoader
import java.io.InputStream
import javax.xml.parsers.DocumentBuilderFactory


class XmlBeanDefinitionReader(resouceLoader: ResourceLoader) : AbstractBeanDefinitionReader(resouceLoader) {

    override fun loadBeanDefinitions(location: String) {
        val inputStream = resourceLoader.getResource(location).getInputStream()
        doLoadBeanDefinitions(inputStream)
    }

    fun doLoadBeanDefinitions(inputStream: InputStream) {
        val factory = DocumentBuilderFactory.newInstance()
        val docBuilder = factory.newDocumentBuilder()
        val doc = docBuilder.parse(inputStream)
        registerBeanDefinitions(doc)
        inputStream.close()
    }

    fun registerBeanDefinitions(doc: Document) {
        val element = doc.documentElement
        parseBeanDefinitions(element)
    }


    protected fun parseBeanDefinitions(root: Element) {
        val nl = root.childNodes
        for (i in 0 until nl.length) {
            val node = nl.item(i)
            if (node is Element) {
                processBeanDefinition(node)
            }
        }
    }

    protected fun processBeanDefinition(ele: Element) {
        val name = ele.getAttribute("name")
        val className = ele.getAttribute("class")
        val beanDefinition = BeanDefinition(name, className)
        processProperty(ele, beanDefinition)
        this.registry[name] = beanDefinition
    }

    private fun processProperty(ele: Element, beanDefinition: BeanDefinition) {
        val propertyNode = ele.getElementsByTagName("property")
        for (i in 0 until propertyNode.length) {
            val node = propertyNode.item(i)
            if (node is Element) {
                val name = node.getAttribute("name")
                val value = node.getAttribute("value")
                beanDefinition.propertyValues.addPropertyValue(PropertyValue(name, value))
            }
        }
    }


}