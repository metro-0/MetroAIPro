package com.metroai.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

object ThemeManager {
    var primaryColor by mutableStateOf(Color(0xFF00FFE0))
    var backgroundColor by mutableStateOf(Color(0xFF0F0C29))

    fun setNeonTheme() {
        primaryColor = Color(0xFF00FFE0)
        backgroundColor = Color(0xFF0F0C29)
    }

    fun setPurpleTheme() {
        primaryColor = Color(0xFFFF00FF)
        backgroundColor = Color(0xFF1A0F3D)
    }

    fun setBlueTheme() {
        primaryColor = Color(0xFF00BFFF)
        backgroundColor = Color(0xFF0A1F44)
    }
}