package com.example.chatflow.di;

import com.example.chatflow.domain.repository.ChatRepository;
import com.example.chatflow.domain.usecase.GetPagedChatsUseCase;
import com.example.chatflow.domain.usecase.ObserveApiTimeMsUseCase;
import com.example.chatflow.domain.usecase.ObserveCachedChatsUseCase;
import com.example.chatflow.domain.usecase.SyncChatsOnceUseCase;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\r"}, d2 = {"Lcom/example/chatflow/di/UseCaseModule;", "", "()V", "provideGetPagedChats", "Lcom/example/chatflow/domain/usecase/GetPagedChatsUseCase;", "repo", "Lcom/example/chatflow/domain/repository/ChatRepository;", "provideObserveApiTimeMs", "Lcom/example/chatflow/domain/usecase/ObserveApiTimeMsUseCase;", "provideObserveCached", "Lcom/example/chatflow/domain/usecase/ObserveCachedChatsUseCase;", "provideSyncOnce", "Lcom/example/chatflow/domain/usecase/SyncChatsOnceUseCase;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class UseCaseModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.chatflow.di.UseCaseModule INSTANCE = null;
    
    private UseCaseModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.example.chatflow.domain.usecase.GetPagedChatsUseCase provideGetPagedChats(@org.jetbrains.annotations.NotNull()
    com.example.chatflow.domain.repository.ChatRepository repo) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.example.chatflow.domain.usecase.ObserveCachedChatsUseCase provideObserveCached(@org.jetbrains.annotations.NotNull()
    com.example.chatflow.domain.repository.ChatRepository repo) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.example.chatflow.domain.usecase.SyncChatsOnceUseCase provideSyncOnce(@org.jetbrains.annotations.NotNull()
    com.example.chatflow.domain.repository.ChatRepository repo) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.example.chatflow.domain.usecase.ObserveApiTimeMsUseCase provideObserveApiTimeMs(@org.jetbrains.annotations.NotNull()
    com.example.chatflow.domain.repository.ChatRepository repo) {
        return null;
    }
}