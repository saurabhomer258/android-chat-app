package com.example.chatflow.di

import com.example.chatflow.data.repo.ChatsRepositoryImpl
import com.example.chatflow.domain.repository.ChatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    
    @Binds @Singleton
    abstract fun bindChatRepository(impl: ChatsRepositoryImpl): ChatRepository
}
