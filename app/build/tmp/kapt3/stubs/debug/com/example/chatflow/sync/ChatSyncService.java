package com.example.chatflow.sync;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleService;
import com.example.chatflow.domain.usecase.SyncChatsOnceUseCase;
import dagger.hilt.android.AndroidEntryPoint;
import kotlinx.coroutines.*;
import javax.inject.Inject;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f\u00a8\u0006\u0012"}, d2 = {"Lcom/example/chatflow/sync/ChatSyncService;", "Landroidx/lifecycle/LifecycleService;", "()V", "serviceJob", "Lkotlinx/coroutines/CompletableJob;", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "syncOnce", "Lcom/example/chatflow/domain/usecase/SyncChatsOnceUseCase;", "getSyncOnce", "()Lcom/example/chatflow/domain/usecase/SyncChatsOnceUseCase;", "setSyncOnce", "(Lcom/example/chatflow/domain/usecase/SyncChatsOnceUseCase;)V", "createNotification", "Landroid/app/Notification;", "onCreate", "", "onDestroy", "app_debug"})
public final class ChatSyncService extends androidx.lifecycle.LifecycleService {
    @javax.inject.Inject()
    public com.example.chatflow.domain.usecase.SyncChatsOnceUseCase syncOnce;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CompletableJob serviceJob = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope serviceScope = null;
    
    public ChatSyncService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.chatflow.domain.usecase.SyncChatsOnceUseCase getSyncOnce() {
        return null;
    }
    
    public final void setSyncOnce(@org.jetbrains.annotations.NotNull()
    com.example.chatflow.domain.usecase.SyncChatsOnceUseCase p0) {
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    private final android.app.Notification createNotification() {
        return null;
    }
}