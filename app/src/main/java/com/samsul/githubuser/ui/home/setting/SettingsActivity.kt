package com.samsul.githubuser.ui.home.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.samsul.githubuser.data.model.Reminder
import com.samsul.githubuser.databinding.ActivitySettingsBinding
import com.samsul.githubuser.preference.ReminderPreference
import com.samsul.githubuser.receiver.AlarmReceiver

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var reminder: Reminder
    private lateinit var alarmReceiver: AlarmReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val reminderPreference = ReminderPreference(this)
        if(reminderPreference.getReminder().isReminded) {
            binding.switch1.isChecked = true
        } else {
            binding.switch1.isChecked = false
        }

        alarmReceiver = AlarmReceiver()

        binding.switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                saveReminder(true)
                alarmReceiver.setRepeatingAlarm(this, "RepeatingAlarm", "09:00", "Github_Reminder")

            } else {
                saveReminder(false)
                alarmReceiver.cencalAlarm(this)
            }
        }


    }

    private fun saveReminder(state: Boolean) {

        val reminderPreference =ReminderPreference(this)
        reminder = Reminder()

        reminder.isReminded = state
        reminderPreference.setReminder(reminder)

    }
}