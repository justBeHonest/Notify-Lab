package com.ahmetaliasik.notifylab.notification

import android.app.NotificationManager
import android.app.PendingIntent
import androidx.core.app.RemoteInput
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.ahmetaliasik.notifylab.MainActivity
import com.ahmetaliasik.notifylab.R

class NotificationHelper(
    private val context: Context
) {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)

    private val messagingStyle = NotificationCompat.MessagingStyle("You")

    val notification = NotificationCompat.Builder(context, NotificationChannels.CHAT_CHANNEL_ID).setSmallIcon(R.drawable.ic_launcher_foreground)
        .setStyle(messagingStyle)
        .setContentIntent(createContentIntent())
        .setAutoCancel(true)
        .addAction(createReplyAction())
        .build()

    private fun createContentIntent() : PendingIntent {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            putExtra("chat_id", "ali_chat")
        }
        return PendingIntent.getActivity(
            context,
            101,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun createReplyAction(): NotificationCompat.Action {
        val remoteInput =
            RemoteInput.Builder(ReplyReceiver.KEY_TEXT_REPLY).setLabel("Yanıtla").build()

        val intent = Intent(context, ReplyReceiver::class.java)

        val pendingIntent = PendingIntent.getBroadcast(
            context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        )


        return NotificationCompat.Action.Builder(
            R.drawable.ic_launcher_foreground, "Yanıtla", pendingIntent
        ).addRemoteInput(remoteInput).build()
    }

    fun showChatMessage(
        sender: String, message: String
    ) {
        messagingStyle.addMessage(
            message,
            System.currentTimeMillis(),
            sender,
        )
        val notification = NotificationCompat.Builder(
            context, NotificationChannels.CHAT_CHANNEL_ID
        ).setSmallIcon(R.drawable.ic_launcher_foreground).setStyle(messagingStyle).setContentIntent(createContentIntent())
            .addAction(createReplyAction()).setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true).build()

        notificationManager.notify(
            CHAT_NOTIFICATION_ID, notification
        )
    }

    fun showSimpleChatNotification() {
        val notification = NotificationCompat.Builder(
            context, NotificationChannels.CHAT_CHANNEL_ID
        ).setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle("Ali")
            .setContentText("Hello!").setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true).build()

        notificationManager.notify(
            CHAT_NOTIFICATION_ID, notification
        )
    }

    companion object {
        private const val CHAT_NOTIFICATION_ID = 1001
    }
}