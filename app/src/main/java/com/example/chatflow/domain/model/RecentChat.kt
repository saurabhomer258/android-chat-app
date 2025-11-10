package com.example.chatflow.domain.model

data class RecentChat(
    val id: Int,
    val name: String,
    val avatarUrl: String?,
    val lastMessage: String,
    val unreadCount: Int,
    val lastSeen: Long,
    val isOnline: Boolean,
    val isTyping: Boolean = false
)
