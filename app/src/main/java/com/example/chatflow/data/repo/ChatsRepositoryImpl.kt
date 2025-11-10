package com.example.chatflow.data.repo

import com.example.chatflow.data.local.ChatDao
import com.example.chatflow.data.local.RecentChatEntity
import com.example.chatflow.data.remote.ApiService
import com.example.chatflow.domain.model.RecentChat
import com.example.chatflow.util.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import androidx.room.withTransaction
import com.example.chatflow.data.local.AppDatabase
import com.example.chatflow.data.remote.ApiTimeHolder
import com.example.chatflow.domain.repository.ChatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ChatsRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val dao: ChatDao,
    private val db: AppDatabase
) : ChatRepository {

    override fun getCachedChats(): Flow<List<RecentChat>> =
        dao.getChatsFlow().map { list -> list.map { it.toDomain() } }
    
    override fun getChats(limit: Int, offset: Int): Flow<ResultState<List<RecentChat>>> = flow {
        if (offset == 0) emit(ResultState.Loading)
        try {
            val resp = api.getUsers()
            val entities = resp.users.map { u ->
                RecentChatEntity(
                    id = u.id,
                    name = "${u.firstName} ${u.lastName}",
                    avatarUrl = u.image,
                    lastMessage = "Hello from ${u.firstName}",
                    unreadCount = (0..3).random(),
                    lastSeen = System.currentTimeMillis() - (0..3_600_000).random(),
                    isOnline = listOf(true, false).random()
                )
            }
            dao.insertAll(entities)
            kotlinx.coroutines.delay(500)
            val page = entities.drop(offset).take(limit).map { it.toDomain() }
            
            emit(ResultState.Success(page))
        } catch (e: Exception) {
            emit(ResultState.Error(e.localizedMessage ?: "Unknown error"))
        }
    }.flowOn(Dispatchers.IO)
    
    
    override suspend fun syncOnce() {
        val resp = api.getUsers()
        val entities = resp.users.map { u ->
            RecentChatEntity(
                id = u.id,
                name = "${u.firstName} ${u.lastName}",
                avatarUrl = u.image,
                lastMessage = "Hello from ${u.firstName}",
                unreadCount = (0..3).random(),
                lastSeen = System.currentTimeMillis() - (0..3_600_000).random(),
                isOnline = listOf(true, false).random()
            )
        }
        
        db.withTransaction {
            dao.clearAll()
            dao.insertAll(entities)
        }
    }
    override fun observeApiTimeMs(): Flow<Long?> = ApiTimeHolder.lastResponseTimeMs
}
