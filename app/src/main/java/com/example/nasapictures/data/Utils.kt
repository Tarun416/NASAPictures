package com.example.nasapictures.data

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class Utils
{
    companion object
    {

    @Throws(IOException::class)
    fun readJSONDataFromFile(): String {
        var inputStream: InputStream? = null
        val builder = StringBuilder()
        try {
            val file = "res/raw/picture.json"
             inputStream = this.javaClass.classLoader.getResourceAsStream(file)
            var jsonString: String? = null
            val bufferedReader = BufferedReader(
                    InputStreamReader(inputStream, "UTF-8")
            )
            while (bufferedReader.readLine().also({ jsonString = it }) != null) {
                builder.append(jsonString)
            }
        } finally {
            inputStream?.close()
        }
        return String(builder)
    }
}}