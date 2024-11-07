package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.components.ClickableAnimatedImage
import com.example.myapplication.components.ResetButton

@Composable
fun CookieScreen(modifier: Modifier = Modifier, cookieViewModel: CookieViewModel = viewModel()) {
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

        ClickableAnimatedImage(cookieViewModel = cookieViewModel)

        Spacer(modifier = Modifier.height(16.dp))
        ResetButton(
            cookieViewModel = cookieViewModel,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}