package com.example.chatflow.di

import com.example.chatflow.domain.repository.ChatRepository
import com.example.chatflow.domain.usecase.GetPagedChatsUseCase
import com.example.chatflow.domain.usecase.ObserveApiTimeMsUseCase
import com.example.chatflow.domain.usecase.ObserveCachedChatsUseCase
import com.example.chatflow.domain.usecase.SyncChatsOnceUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    
    @Provides @Singleton
    fun provideGetPagedChats(repo: ChatRepository) = GetPagedChatsUseCase(repo)
    
    @Provides @Singleton
    fun provideObserveCached(repo: ChatRepository) = ObserveCachedChatsUseCase(repo)
    
    @Provides @Singleton
    fun provideSyncOnce(repo: ChatRepository) = SyncChatsOnceUseCase(repo)
    
    @Provides @Singleton
    fun provideObserveApiTimeMs(repo: ChatRepository) = ObserveApiTimeMsUseCase(repo)

}
