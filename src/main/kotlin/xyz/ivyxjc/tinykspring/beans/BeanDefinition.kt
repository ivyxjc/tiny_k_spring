package xyz.ivyxjc.tinykspring.beans


class BeanDefinition constructor(beanName: String, beanClassName: String) {

    var beanName: String
    val beanClassName: String

    var bean: Any? = null

    val beanClass: Class<*>
        get() = Class.forName(beanClassName)

    var propertyValues: PropertyValues = PropertyValues()

    init {
        this.beanClassName = beanClassName
        this.beanName = beanName
    }


}