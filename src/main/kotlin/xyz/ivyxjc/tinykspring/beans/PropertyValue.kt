package xyz.ivyxjc.tinykspring.beans


data class PropertyValue(var beanName: String, var bean: Any)


class PropertyValues {
    var values: MutableList<PropertyValue>

    init {
        values = ArrayList()
    }

    fun addPropertyValue(propertyValue: PropertyValue) {
        values.add(propertyValue)
    }
}