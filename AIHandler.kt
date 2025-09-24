package com.metroai.ai

import android.content.Context
import com.metroai.utils.TTSHelper
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

object AIHandler {
    fun getAIResponse(prompt: String, context: Context) {
        val client = OkHttpClient()
        val json = """
            {
              "model": "gpt-4-mini",
              "messages": [{"role":"user","content":"$prompt"}]
            }
        """.trimIndent()
        val body = json.toRequestBody("application/json".toMediaType())
        val request = Request.Builder()
            .url("https://api.openai.com/v1/chat/completions")
            .addHeader("Authorization", "Bearer YOUR_API_KEY")
            .post(body)
            .build()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                val answer = JSONObject(response.body!!.string())
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content")
                TTSHelper.speak(context, answer)
            }
        })
    }
}