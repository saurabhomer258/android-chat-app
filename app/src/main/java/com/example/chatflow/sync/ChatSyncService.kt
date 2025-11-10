package com.example.chatflow.sync

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.example.chatflow.domain.usecase.SyncChatsOnceUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class ChatSyncService : LifecycleService() {
    
    @Inject
    lateinit var syncOnce: SyncChatsOnceUseCase
    
    private val serviceJob = SupervisorJob()
    private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)
    
    override fun onCreate() {
        super.onCreate()
        
        startForeground(1, createNotification()) // foreground mode start
        
        serviceScope.launch {
            while (isActive) {
                try {
                    syncOnce()
                } catch (_: Exception) {
                }
                delay(15_000) // 30 sec polling
            }
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel() // ✅ Stop coroutine loop
    }
    
    private fun createNotification(): Notification {
        val channelId = "chat_sync_channel"
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Chat Sync Service",
                NotificationManager.IMPORTANCE_MIN // Silent background sync
            )
            manager.createNotificationChannel(channel)
        }
        
        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("ChatFlow Sync Active")
            .setContentText("Syncing chats in background…")
            .setSmallIcon(android.R.drawable.stat_notify_sync)
            .setOngoing(true)
            .build()
    }
}
