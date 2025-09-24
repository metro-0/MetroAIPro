package com.metroai.ai

import android.content.Context
import com.metroai.data.UserCommandDatabase

object CustomCommands {
    fun checkCommand(trigger: String, context: Context): String? {
        val db = UserCommandDatabase.getDatabase(context)
        val command = db.commandDao().getAll().find {
            it.trigger.equals(trigger, true)
        }
        return command?.response
    }

    fun addCommand(trigger: String, response: String, context: Context) {
        val db = UserCommandDatabase.getDatabase(context)
        db.commandDao().insert(com.metroai.data.UserCommand(trigger = trigger, response = response))
    }
}