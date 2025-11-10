package com.example.chatflow.domain.usecase

import com.example.chatflow.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow

class ObserveApiTimeMsUseCase(private val repo: ChatRepository) {
    operator fun invoke(): Flow<Long?> = repo.observeApiTimeMs()
}