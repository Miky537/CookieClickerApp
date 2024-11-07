package com.example.myapplication

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Navigation() {
    val selectedIndex = remember { mutableStateOf(0) }
    val cookieViewModel: CookieViewModel = viewModel()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.cookie_icon_pic),
                            contentDescription = "Cookie"
                        )
                    },
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
            0 -> CookieScreen(
                modifier = Modifier.padding(innerPadding),
                cookieViewModel = cookieViewModel
            )

            1 -> StoreScreen(
                modifier = Modifier.padding(innerPadding),
                cookieViewModel = cookieViewModel
            )
        }
    }
}