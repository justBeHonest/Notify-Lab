package com.ahmetaliasik.notifylab.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.RemoteInput

class ReplyReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context, intent: Intent) {
    val replyText = RemoteInput.getResultsFromIntent(intent)
        ?.getCharSequence(KEY_TEXT_REPLY)
        ?.toString()

        if(replyText != null) {
            println("User replied: $replyText")
        }
    }


    companion object {
        const val KEY_TEXT_REPLY = "key_text_reply"
    }
}