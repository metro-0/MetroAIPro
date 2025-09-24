package com.metroai

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import com.metroai.ui.MetroAssistantCoolUI
import com.metroai.utils.TTSHelper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 101)
        TTSHelper.init(this)
        setContent {
            MetroAssistantCoolUI()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        TTSHelper.shutdown()
    }
}