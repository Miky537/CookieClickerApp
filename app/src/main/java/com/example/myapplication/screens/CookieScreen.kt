package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.CookieViewModel
import com.example.myapplication.components.ClickableAnimatedImage
import com.example.myapplication.components.ResetButton

@Composable
fun CookieScreen(modifier: Modifier = Modifier, cookieViewModel: CookieViewModel = viewModel()) {
    val totalCookies = cookieViewModel.totalCookies.value
    val offset = Offset(5.0f, 10.0f)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(16.dp)
    )
    {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            ClickableAnimatedImage(cookieViewModel = cookieViewModel)
            Spacer(modifier = Modifier.height(100.dp))
            Text(
                text = totalCookies,
                style = TextStyle(
                    fontSize = 24.sp,
                    shadow = Shadow(
                        color = Color.Gray, offset = offset, blurRadius = 2f
                    )
                ),
                fontSize = 50.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(top = 16.dp)

            )

            Spacer(modifier = Modifier.height(16.dp))
            ResetButton(
                cookieViewModel = cookieViewModel,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}