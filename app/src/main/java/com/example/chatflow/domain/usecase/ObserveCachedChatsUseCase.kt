package com.example.chatflow.domain.usecase

import com.example.chatflow.domain.model.RecentChat
import com.example.chatflow.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow

class ObserveCachedChatsUseCase(private val repo: ChatRepository) {
    operator fun invoke(): Flow<List<RecentChat>> = repo.getCachedChats()
}
