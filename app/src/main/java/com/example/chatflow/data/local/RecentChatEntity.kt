package com.example.chatflow.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.chatflow.domain.model.RecentChat

@Entity(tableName = "recent_chats")
data class RecentChatEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val avatarUrl: String?,
    val lastMessage: String,
    val unreadCount: Int,
    val lastSeen: Long,
    val isOnline: Boolean
) {
    fun toDomain() = RecentChat(id, name, avatarUrl, lastMessage, unreadCount, lastSeen, isOnline)
}
