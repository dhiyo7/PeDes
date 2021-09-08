package com.plugin.pengaduandesa.config

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.plugin.pengaduandesa.MainActivity
import com.plugin.pengaduandesa.R
import com.plugin.pengaduandesa.utils.PengaduanUtils

class MyFirebaseMessagingService : FirebaseMessagingService(){
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        sendRegistrationToServer(p0)
        println("DEVICE TOKEN " + p0)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        println("DATA " + p0.data)

        Log.d(TAG, "Message data payload: ${p0.data}")

        p0.notification?.let {
            showNotification(it.title.toString(), it.body.toString())
        }
    }


    private fun sendRegistrationToServer(token: String?) {
        // TODO: Implement this method to send token to your app server.
        Log.d(TAG, "sendRegistrationTokenToServer($token)")
            PengaduanUtils.setDeviceToken(applicationContext, token!!)
    }

    private fun showNotification(title:String,body: String) {
        val channelId = getString(R.string.default_notification_channel_id)
        val intent = Intent(this@MyFirebaseMessagingService, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.logo)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setSound(soundUri)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_MAX)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(getString(R.string.default_notification_channel_id),
                "Channel human readable title",
                NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(20, builder.build())
    }

    companion object {

        private const val TAG = "MyFirebaseMsgService"
    }
}