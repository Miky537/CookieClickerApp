package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.viewmodels.CookieViewModel
import com.example.myapplication.components.TableLayout

@Composable
fun StoreScreen(modifier: Modifier = Modifier, cookieViewModel: CookieViewModel = viewModel()) {
    val ovensCounter = cookieViewModel.ovens.value
    val grandmaBakerCounter = cookieViewModel.grandmas.value
    val factoryCounter = cookieViewModel.factories.value

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Store", fontSize = 32.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                cookieViewModel.buyOven()
            }) {
                Text(text = "Buy oven for 100 cookies! (+1c/s)")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                cookieViewModel.buyGrandma()
            }) {
                Text(text = "Buy Grandma for 500 cookies! (+7c/s)")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                cookieViewModel.buyFactory()
            }) {
                Text(text = "Buy cookie factory for 10 000 cookies! (+150c/s)")
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                TableLayout(
                    ovens = ovensCounter ?: 0,
                    grandmas = grandmaBakerCounter ?: 0,
                    factories = factoryCounter ?: 0,
                )
                Spacer(modifier = Modifier.height(16.dp))
                val cookiesPerSecond = ovensCounter + grandmaBakerCounter * 7 + factoryCounter * 150
                Text(
                    text = "You are making $cookiesPerSecond cookies per second!",
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}