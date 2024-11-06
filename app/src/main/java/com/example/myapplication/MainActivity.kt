package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp()
        }
    }
}

@Composable
fun MainApp() {
    val selectedIndex = remember { mutableStateOf(0) }
    val cookieViewModel: CookieViewModel = viewModel()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = "Cookie") },
                    label = { Text("Cookie") },
                    selected = selectedIndex.value == 0,
                    onClick = { selectedIndex.value = 0 },
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Star, contentDescription = "Store") },
                    label = { Text("Store") },
                    selected = selectedIndex.value == 1,
                    onClick = { selectedIndex.value = 1 }
                )
            }
        }
    ) { innerPadding ->
        when (selectedIndex.value) {
            0 -> HomeScreen(modifier = Modifier.padding(innerPadding), cookieViewModel = cookieViewModel)
            1 -> StoreScreen(modifier = Modifier.padding(innerPadding), cookieViewModel = cookieViewModel)
        }
    }
}


@Composable
fun HomeScreen(modifier: Modifier = Modifier, cookieViewModel: CookieViewModel = viewModel()) {
    val totalCookies = cookieViewModel.totalCookies.value

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Cookie amount: $totalCookies",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.cookie),
            contentDescription = "Cookie",
            modifier = Modifier
                .size(200.dp)
                .clickable {
                    cookieViewModel.onCookieClicked()
                }
        )

        Spacer(modifier = Modifier.height(16.dp))
        ResetButton(
            cookieViewModel = cookieViewModel,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun ResetButton(cookieViewModel: CookieViewModel, modifier: Modifier = Modifier) {
    Button(
        onClick = {
            cookieViewModel.resetCookies()
        },
        modifier = modifier.padding(8.dp)
    ) {
        Text(text = "Reset cookies")
    }
}

@Composable
fun StoreScreen(modifier: Modifier = Modifier, cookieViewModel: CookieViewModel = viewModel()) {
    val granmaBakerCounter = cookieViewModel.grandmas.value ?: 0
    val factoryCounter = cookieViewModel.factories.value ?: 0
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
            Text(text = "You are making $cookiesPerSecond cookies per second!", fontSize = 18.sp)
        }
    }
}

@Composable
fun TableLayout(autoClickers: Int, factories: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Auto Clickers", fontSize = 20.sp)
            Text(text = "$autoClickers", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Factories", fontSize = 20.sp)
            Text(text = "$factories", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}