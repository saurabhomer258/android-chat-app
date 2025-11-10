package com.example.chatflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.example.chatflow.ui.chatlist.ChatListScreen
import com.example.chatflow.ui.chatdetail.ChatDetailScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatFlowAppContent()
        }
    }
}

@Composable
fun ChatFlowAppContent() {
    val navController = rememberNavController()
    Surface(color = MaterialTheme.colorScheme.background) {
        NavHost(navController = navController, startDestination = "chatList") {
            composable("chatList") {
                ChatListScreen(onOpenChat = { userId ->
                    navController.navigate("chatDetail/$userId")
                })
            }
            composable("chatDetail/{userId}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("userId")?.toIntOrNull() ?: 0
                ChatDetailScreen(userId = id)
            }
        }
    }
}
