package com.samsul.githubuser.receiver

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build.VERSION_CODES
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.samsul.githubuser.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class AlarmReceiver : BroadcastReceiver() {

    companion object {
        private const val NOTIFICATION_ID = 1
        private const val CHANNEL_ID = "channel_01"
        private const val CHANNEL_NAME = "Github_Reminder"
        private const val TIME_FORMAT = "HH:mm"
        const val EXTRA_MESSAGE = "message"
        const val EXTRA_TYPE = "extra_type"
        private const val ID_REPEATING = 101
    }

    @RequiresApi(VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        sendNotification(context)
    }

    @RequiresApi(VERSION_CODES.O)
    private fun sendNotification(context: Context) {
        val intent = context.packageManager.getLaunchIntentForPackage("com.samsul.githubuser")
        val pendingIntent = PendingIntent.getActivity(context,0,intent,0)
        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle(context.resources.getString(R.string.app_name))
            .setContentText("Cari User favorite anda")
            .setAutoCancel(true)

        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
        builder.setChannelId(CHANNEL_ID)
        notificationManager.createNotificationChannel(channel)

        val notification = builder.build()
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    fun setRepeatingAlarm(context: Context, type: String, time: String, message: String) {
        if(isDateInvalid(time, TIME_FORMAT)) return
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra(EXTRA_MESSAGE, message)
        intent.putExtra(EXTRA_TYPE, type)
        val timeArray = time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]))
        calendar.set(Calendar.SECOND, 0)
        val pendingIntent = PendingIntent.getBroadcast(context, ID_REPEATING, intent, 0)
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)
        Toast.makeText(context, "Alarm setup", Toast.LENGTH_SHORT).show()
    }

    private fun isDateInvalid(time: String, timeFormat: String): Boolean {
        return try {
            val format = SimpleDateFormat(timeFormat, Locale.getDefault())
            format.isLenient = false
            format.parse(time)
            false
        } catch (e: ParseException) {
            true
        }
    }

    fun cencalAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val requestCode = ID_REPEATING
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)
        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)
        Toast.makeText(context, "Alarm dibatalkan", Toast.LENGTH_SHORT).show()
    }
}