package com.example.myapplication

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

data class FoodText(val quote: String)

class FoodTextModel {
    suspend fun fetchQuoteOfTheDay(): FoodText? = withContext(Dispatchers.IO) {
        try {
            val url = URL("https://catfact.ninja/fact")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                val json = JSONObject(response)
                FoodText(json.getString("fact"))
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}