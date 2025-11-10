package com.example.chatflow.domain.usecase

import com.example.chatflow.domain.repository.ChatRepository

class SyncChatsOnceUseCase(private val repo: ChatRepository) {
    suspend operator fun invoke() = repo.syncOnce()
}
