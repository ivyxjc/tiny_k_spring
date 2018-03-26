package xyz.ivyxjc.tinykspring.io

import org.junit.Assert
import org.junit.Test
import xyz.ivyxjc.tinykspring.beans.io.ResourceLoader

class ResourceLoaderTest {

    @Test
    fun test() {
        val resourceLoader = ResourceLoader()
        val resource = resourceLoader.getResource("tinyioc.xml")
        val inputStream = resource.getInputStream()
        Assert.assertNotNull(inputStream)
    }
}