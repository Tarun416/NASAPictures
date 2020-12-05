package com.example.nasapictures.utils

import android.content.Context
import com.example.nasapictures.R
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class Utils
{
    companion object
    {
        @Throws(IOException::class)
         fun readJSONDataFromFile(context : Context): String {
            var inputStream: InputStream? = null
            val builder = StringBuilder()
            try {
                var jsonString: String? = null
                inputStream = context.resources.openRawResource(R.raw.picture)
                val bufferedReader = BufferedReader(
                    InputStreamReader(inputStream, "UTF-8")
                )
                while (bufferedReader.readLine().also({ jsonString = it }) != null) {
                    builder.append(jsonString)
                }
            } finally {
                if (inputStream != null) {
                    inputStream.close()
                }
            }
            return String(builder)
        }
    }
}