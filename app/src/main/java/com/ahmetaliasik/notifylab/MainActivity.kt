package com.ahmetaliasik.notifylab

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ahmetaliasik.notifylab.core.navigation.AppNavGraph
import com.ahmetaliasik.notifylab.notification.NotificationChannels
import com.ahmetaliasik.notifylab.ui.MainScreen
import com.ahmetaliasik.notifylab.ui.theme.NotifyLabTheme

class MainActivity : ComponentActivity() {
    private val chatIdState = mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        NotificationChannels.create(this)
        val startChatId = intent.getStringExtra("chat_id")
        setContent {
            NotifyLabTheme {
                AppNavGraph(startChatId = startChatId)
            }
        }
    }

    override fun onNewIntent(intent : Intent){
        super.onNewIntent(intent)
        chatIdState.value = intent.getStringExtra("chat_id")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotifyLabTheme {
        Greeting("Android")
    }
}