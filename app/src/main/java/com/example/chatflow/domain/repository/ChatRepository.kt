package com.example.chatflow.domain.repository

import com.example.chatflow.domain.model.RecentChat
import com.example.chatflow.util.ResultState
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun getCachedChats(): Flow<List<RecentChat>>
    fun getChats(limit: Int, offset: Int): Flow<ResultState<List<RecentChat>>>
    suspend fun syncOnce()
    
    fun observeApiTimeMs(): Flow<Long?>
}
