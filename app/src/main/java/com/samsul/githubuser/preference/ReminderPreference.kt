package com.samsul.githubuser.preference

import android.content.Context
import com.samsul.githubuser.data.model.Reminder

class ReminderPreference(context: Context) {

    companion object {
        const val PREFS_NAME = "reminder"
        private const val REMINDER = "isReminder"
    }

    private val preference = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setReminder(reminder: Reminder) {
        val editor = preference.edit()
        editor.putBoolean(REMINDER, reminder.isReminded)
        editor.apply()
    }

    fun getReminder(): Reminder {
        val model = Reminder()
        model.isReminded = preference.getBoolean(REMINDER, false)
        return model
    }
}