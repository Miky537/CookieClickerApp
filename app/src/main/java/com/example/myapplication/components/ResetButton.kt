package com.example.myapplication.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.CookieViewModel

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
