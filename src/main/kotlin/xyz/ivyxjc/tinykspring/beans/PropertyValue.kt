package xyz.ivyxjc.tinykspring.beans


data class PropertyValue(val fieldName: String, val value: Any)


class PropertyValues {
    var values: MutableList<PropertyValue>

    init {
        values = ArrayList()
    }

    fun addPropertyValue(propertyValue: PropertyValue) {
        values.add(propertyValue)
    }
}