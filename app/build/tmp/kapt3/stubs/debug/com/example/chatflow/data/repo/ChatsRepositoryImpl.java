package com.example.chatflow.data.repo;

import com.example.chatflow.data.local.ChatDao;
import com.example.chatflow.data.local.RecentChatEntity;
import com.example.chatflow.data.remote.ApiService;
import com.example.chatflow.domain.model.RecentChat;
import com.example.chatflow.util.ResultState;
import kotlinx.coroutines.flow.Flow;
import com.example.chatflow.data.local.AppDatabase;
import com.example.chatflow.data.remote.ApiTimeHolder;
import com.example.chatflow.domain.repository.ChatRepository;
import kotlinx.coroutines.Dispatchers;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nH\u0016J*\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u000e0\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0010\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\nH\u0016J\u000e\u0010\u0014\u001a\u00020\u0015H\u0096@\u00a2\u0006\u0002\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/example/chatflow/data/repo/ChatsRepositoryImpl;", "Lcom/example/chatflow/domain/repository/ChatRepository;", "api", "Lcom/example/chatflow/data/remote/ApiService;", "dao", "Lcom/example/chatflow/data/local/ChatDao;", "db", "Lcom/example/chatflow/data/local/AppDatabase;", "(Lcom/example/chatflow/data/remote/ApiService;Lcom/example/chatflow/data/local/ChatDao;Lcom/example/chatflow/data/local/AppDatabase;)V", "getCachedChats", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/chatflow/domain/model/RecentChat;", "getChats", "Lcom/example/chatflow/util/ResultState;", "limit", "", "offset", "observeApiTimeMs", "", "syncOnce", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class ChatsRepositoryImpl implements com.example.chatflow.domain.repository.ChatRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.chatflow.data.remote.ApiService api = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.chatflow.data.local.ChatDao dao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.chatflow.data.local.AppDatabase db = null;
    
    @javax.inject.Inject()
    public ChatsRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.example.chatflow.data.remote.ApiService api, @org.jetbrains.annotations.NotNull()
    com.example.chatflow.data.local.ChatDao dao, @org.jetbrains.annotations.NotNull()
    com.example.chatflow.data.local.AppDatabase db) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.example.chatflow.domain.model.RecentChat>> getCachedChats() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.example.chatflow.util.ResultState<java.util.List<com.example.chatflow.domain.model.RecentChat>>> getChats(int limit, int offset) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object syncOnce(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.lang.Long> observeApiTimeMs() {
        return null;
    }
}