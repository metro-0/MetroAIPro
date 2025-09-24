package com.metroai.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.sin

@Composable
fun AnimatedAvatar(modifier: Modifier = Modifier, emotion: String = "neutral") {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        0f, 360f, infiniteRepeatable(animation = tween(6000), repeatMode = RepeatMode.Restart)
    )
    val pulse by infiniteTransition.animateFloat(
        0.8f, 1.2f, infiniteRepeatable(animation = tween(1000), repeatMode = RepeatMode.Reverse)
    )

    Canvas(modifier = modifier) {
        val center = Offset(size.width / 2, size.height / 2)
        val radius = size.minDimension / 3 * pulse
        val color = when (emotion.lowercase()) {
            "happy" -> Color(0xFFFFFF00)
            "sad" -> Color(0xFF0000FF)
            "thinking" -> Color(0xFF00FFFF)
            else -> Color(0xFF00FFE0)
        }

        for (i in 1..3) {
            rotate(rotation + i * 30f, pivot = center) {
                drawCircle(color.copy(alpha = 0.4f / i), radius = radius * i / 3, center = center)
            }
        }

        drawCircle(color, radius = radius / 2, center = center)
    }
}