package com.example.myapplication.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.viewmodels.CookieViewModel
import com.example.myapplication.R

@Composable
fun ClickableAnimatedImage(cookieViewModel: CookieViewModel) {
    var isClicked by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isClicked) 1.2f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    Image(
        painter = painterResource(id = R.drawable.cookie),
        contentDescription = "Cookie",
        modifier = Modifier
            .size(200.dp)
            .scale(scale)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                isClicked = true
                cookieViewModel.onCookieClicked()
            }
    )
    LaunchedEffect(isClicked) {
        if (isClicked) {
            kotlinx.coroutines.delay(100)
            isClicked = false
        }
    }
}
