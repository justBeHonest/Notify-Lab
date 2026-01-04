package com.ahmetaliasik.notifylab.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

object NotificationChannels {
    const val CHAT_CHANNEL_ID = "chat_channel"
    const val PROMO_CHANNEL_ID = "promo_channel"

    fun create(context : Context){
        val notificationManager = context.getSystemService(NotificationManager::class.java)

        val chatChannel = NotificationChannel(
            CHAT_CHANNEL_ID,
            "Chat Messages",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Incoming chat messages"
        }

        val promoChannel = NotificationChannel(
            PROMO_CHANNEL_ID,
            "Promotions",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "Campaigns and promotions"
        }

        notificationManager.createNotificationChannels(
            listOf(chatChannel,promoChannel)
        )
    }
}