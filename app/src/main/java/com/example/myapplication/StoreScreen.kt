package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.components.TableLayout

@Composable
fun StoreScreen(modifier: Modifier = Modifier, cookieViewModel: CookieViewModel = viewModel()) {
    val granmaBakerCounter = cookieViewModel.grandmas.value
    val factoryCounter = cookieViewModel.factories.value
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Store", fontSize = 32.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            cookieViewModel.buyGrandma()
        }) {
            Text(text = "Buy Granma for 500 cookies! (+2c/s)")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            cookieViewModel.buyFactory()
        }) {
            Text(text = "Buy cookie factory for 10 000 cookies! (+100c/s)")
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            TableLayout(
                autoClickers = cookieViewModel.grandmas.value ?: 0,
                factories = cookieViewModel.factories.value ?: 0,
            )
            Spacer(modifier = Modifier.height(16.dp))
            val cookiesPerSecond = granmaBakerCounter * 2 + factoryCounter * 100
            Text(
                text = "You are making $cookiesPerSecond cookies per second!",
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
