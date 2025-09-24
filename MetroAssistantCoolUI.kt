package com.metroai.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.metroai.ai.AIHandler
import com.metroai.ai.CustomCommands
import com.metroai.utils.TTSHelper

@Composable
fun MetroAssistantCoolUI() {
    var chatMessages by remember { mutableStateOf(listOf("Hi, I'm Metro!")) }
    var currentEmotion by remember { mutableStateOf("neutral") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ThemeManager.backgroundColor)
    ) {
        AnimatedAvatar(modifier = Modifier.size(200.dp).align(Alignment.TopCenter), emotion = currentEmotion)

        WaveformAnimation(modifier = Modifier.fillMaxWidth().height(120.dp).align(Alignment.BottomCenter))

        FloatingActionButton(
            onClick = {
                val userInput = "play music" // replace with actual voice input
                val context = LocalContext.current
                val customResponse = CustomCommands.checkCommand(userInput, context)
                if (customResponse != null) {
                    TTSHelper.speak(context, customResponse)
                    chatMessages = chatMessages + customResponse
                    currentEmotion = "happy"
                } else {
                    AIHandler.getAIResponse(userInput, context)
                    chatMessages = chatMessages + userInput
                    currentEmotion = "thinking"
                }
            },
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 40.dp),
            backgroundColor = ThemeManager.primaryColor
        ) {
            Icon(Icons.Default.Mic, contentDescription = "Activate")
        }

        LazyColumn(modifier = Modifier.align(Alignment.Center).padding(horizontal = 16.dp)) {
            items(chatMessages) { message ->
                // For simplicity, using Box as chat