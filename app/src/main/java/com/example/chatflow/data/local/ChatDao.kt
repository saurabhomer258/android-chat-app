package com.example.chatflow.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import androidx.paging.PagingSource
import androidx.room.Transaction

@Dao
interface ChatDao {
    
    @Query("SELECT * FROM recent_chats ORDER BY lastSeen DESC")
    fun getChatsFlow(): Flow<List<RecentChatEntity>>
    
    // Return the row IDs so Room is happy for list inserts
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(chats: List<RecentChatEntity>): List<Long>
    
    // DELETE must return void(Unit) or Int; Int is safest
    @Query("DELETE FROM recent_chats")
    suspend fun clearAll(): Int
    
    @Query("SELECT * FROM recent_chats ORDER BY lastSeen DESC")
    fun getPagedChats(): PagingSource<Int, RecentChatEntity>
    
    
    // ✅ Page-wise read from Room (for offline pagination)
    @Query("SELECT * FROM recent_chats ORDER BY lastSeen DESC LIMIT :limit OFFSET :offset")
    suspend fun getChunk(limit: Int, offset: Int): List<RecentChatEntity>
    
    
    @Transaction
    suspend fun replaceAll(chats: List<RecentChatEntity>) {
        clearAll()
        insertAll(chats)
    }
    
    @Query("SELECT * FROM recent_chats WHERE name LIKE :searchText || '%' ORDER BY lastSeen DESC LIMIT :limit OFFSET :offset")
    suspend fun getChunkByName(
        searchText: String,
        limit: Int,
        offset: Int
    ): List<RecentChatEntity>
}




