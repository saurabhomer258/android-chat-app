package com.example.chatflow.domain.usecase

import com.example.chatflow.domain.model.RecentChat
import com.example.chatflow.domain.repository.ChatRepository
import com.example.chatflow.util.ResultState
import kotlinx.coroutines.flow.Flow


class GetPagedChatsUseCase(private val repo: ChatRepository) {
    operator fun invoke(limit: Int, offset: Int): Flow<ResultState<List<RecentChat>>> =
        repo.getChats(limit, offset)
}
