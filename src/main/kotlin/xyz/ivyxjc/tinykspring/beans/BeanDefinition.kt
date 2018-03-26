package xyz.ivyxjc.tinykspring.beans


class BeanDefinition constructor(val beanName: String, val beanClassName: String) {

    var bean: Any? = null

    val beanClass: Class<*>
        get() = Class.forName(beanClassName)

    var propertyValues: PropertyValues = PropertyValues()


}