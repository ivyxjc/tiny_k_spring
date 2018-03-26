package xyz.ivyxjc.tinykspring.beans.io

import java.io.InputStream
import java.net.URL


interface Resource {
    fun getInputStream(): InputStream
}


class UrlResource(val url: URL) : Resource {
    override fun getInputStream(): InputStream {
        val urlConnection = url.openConnection()
        urlConnection.connect()
        return urlConnection.getInputStream()
    }
}

class ResourceLoader {
    fun getResource(location: String): Resource {
        val resource = this.javaClass.classLoader.getResource(location)
        return UrlResource(resource)
    }
}