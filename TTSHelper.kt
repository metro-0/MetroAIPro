package com.metroai.utils

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*

object TTSHelper {
    private var tts: TextToSpeech? = null

    fun init(context: Context) {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) tts?.language = Locale.US
        }
    }

    fun speak(context: Context, message: String) {
        if (tts == null) init(context)
        tts?.speak(message, TextToSpeech.QUEUE_FLUSH, null, "MetroAI")
    }

    fun shutdown() {
        tts?.shutdown()
    }
}