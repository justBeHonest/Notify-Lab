package com.ahmetaliasik.notifylab.notification

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val data = remoteMessage.data
        val chatId = data["chat_id"]
        val message = data["message"]

        if(chatId != null && message != null) {
            NotificationHelper(applicationContext)
                .showChatMessage(
                    sender = "Ali",
                    message = message
                )
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        println("Fcm Token : $token")
        Log.d("FCM_TOKEN", token)
    }
}