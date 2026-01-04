package com.ahmetaliasik.notifylab.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahmetaliasik.notifylab.ui.ChatScreen
import com.ahmetaliasik.notifylab.ui.MainScreen

@Composable
fun AppNavGraph(startChatId : String?) {
    val navController = rememberNavController()

    LaunchedEffect(startChatId) {
        startChatId?.let {
            navController.navigate("chat/$it") {
                launchSingleTop = true
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = "home",
    ) {
        composable("home") { MainScreen() }
        composable("chat/{chatId}") {
            ChatScreen(chatId = it.arguments?.getString("chatId"))
        }
    }
}