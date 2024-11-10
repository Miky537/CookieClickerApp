package com.example.myapplication.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.viewmodels.CookieViewModel

@Composable
fun ResetButton(cookieViewModel: CookieViewModel, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Button(
        onClick = {
            if (expanded) {
                cookieViewModel.resetCookies()
            }
            expanded = !expanded
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
        modifier = modifier
            .padding(8.dp)
            .animateContentSize()
            .focusRequester(focusRequester)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.restart_icon),
            contentDescription = null,
        )
        if (expanded) {
            Text(
                text = "Reset cookies",
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }

    if (expanded) {
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
            focusManager.clearFocus()
        }
    }
}
