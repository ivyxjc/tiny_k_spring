package xyz.ivyxjc.tinykspring.beans


data class PropertyValue(val fieldName: String, val value: Any)


class PropertyValues {
    var values = mutableListOf<PropertyValue>()

    fun addPropertyValue(propertyValue: PropertyValue) {
        values.add(propertyValue)
    }
}