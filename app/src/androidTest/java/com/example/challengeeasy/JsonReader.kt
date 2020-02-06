package com.example.challengeeasy

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import java.io.FileReader
import java.io.InputStreamReader

object JsonReader {

    const val FULL_JSON_FILE_PATH = "src/androidTest/fixture/"
    private const val JSON_FILE_PATH = "fixture/"

    inline fun <reified T> getObjectFromJsonFile(fileName: String) : T {
        val reader = JsonReader(FileReader(FULL_JSON_FILE_PATH + fileName))
        return Gson().fromJson<T>(reader, T::class.java)
    }

    fun getStringFromJsonFile(fileName: String) : String? {
        val reader: InputStreamReader? = javaClass.classLoader?.getResourceAsStream(JSON_FILE_PATH + fileName)?.reader()
        var content: String? = "Content incorrect"
        reader.use {
            content = reader?.readText()
        }
        return content
    }
}