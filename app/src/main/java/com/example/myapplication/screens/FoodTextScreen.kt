package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.FoodTextViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

@Composable
fun FoodTextScreen(modifier: Modifier = Modifier, foodTextModel: FoodTextViewModel = viewModel()) {

    val quote by foodTextModel.quote

    LaunchedEffect(Unit) {
        foodTextModel.fetchQuote()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        Text(
            text = "Cat facts!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                Modifier
                    .fillMaxWidth(0.95f)
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 20.dp))
                    .background(color = Color(0xff4a70c2)),
                contentAlignment = Alignment.Center
            ) {
                if (quote == null) {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(20.dp)
                    )
                } else {
                    Text(
                        text = quote?.quote ?: "Failed to load quote", // Accessing the 'quote' property of 'FoodText'
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .fillMaxWidth(0.95f)
                            .padding(20.dp)
                    )

                }
            }
        }
    }
}


suspend fun fetchQuoteOfTheDay(): String? = withContext(Dispatchers.IO) {
    try {
        val url = URL("https://catfact.ninja/fact")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            val response = connection.inputStream.bufferedReader().use { it.readText() }
            val json = JSONObject(response)
            json.getString("fact")
        } else {
            null
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
