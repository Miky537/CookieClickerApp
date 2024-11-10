package com.example.myapplication.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.CookieViewModel
import com.example.myapplication.FoodTextViewModel
import com.example.myapplication.R
import com.example.myapplication.screens.CookieScreen
import com.example.myapplication.screens.FoodTextScreen
import com.example.myapplication.screens.StoreScreen


@Composable
fun Navigation() {
    val selectedIndex = remember { mutableStateOf(0) }
    val cookieViewModel: CookieViewModel = viewModel()
    val foodViewModel: FoodTextViewModel = viewModel()

    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.graphicsLayer { shadowElevation = 15.dp.toPx() },
                containerColor = Color(0xFFD7B899),
                contentColor = Color.White
            ) {
                NavigationBar(
                    containerColor = Color(0xFFD7B899),
                    contentColor = Color.White
                ) {
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.cookie_icon_pic),
                                contentDescription = null
                            )
                        },
                        label = { Text("Cookie") },
                        selected = selectedIndex.value == 0,
                        alwaysShowLabel = true,
                        onClick = { selectedIndex.value = 0 },
                    )
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.store_icon),
                                contentDescription = null,
                            )
                        },
                        label = { Text("Store") },
                        selected = selectedIndex.value == 1,
                        alwaysShowLabel = true,
                        onClick = { selectedIndex.value = 1 }


                    )
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.pet_icon),
                                contentDescription = null,
                            )
                        },
                        label = { Text("Facts") },
                        selected = selectedIndex.value == 2,
                        alwaysShowLabel = true,
                        onClick = { selectedIndex.value = 2 }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFD7B899))
                .padding(innerPadding)
        ) {
            when (selectedIndex.value) {
                0 -> CookieScreen(
                    modifier = Modifier.fillMaxSize(),
                    cookieViewModel = cookieViewModel
                )

                1 -> StoreScreen(
                    modifier = Modifier.fillMaxSize(),
                    cookieViewModel = cookieViewModel
                )
                2 -> FoodTextScreen(
                    modifier = Modifier.fillMaxSize(),
                    foodTextModel = foodViewModel
                )
            }
        }
    }
}