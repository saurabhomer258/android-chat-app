package com.example.chatflow.domain.usecase;

import com.example.chatflow.domain.model.RecentChat;
import com.example.chatflow.domain.repository.ChatRepository;
import com.example.chatflow.util.ResultState;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J+\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00070\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/example/chatflow/domain/usecase/GetPagedChatsUseCase;", "", "repo", "Lcom/example/chatflow/domain/repository/ChatRepository;", "(Lcom/example/chatflow/domain/repository/ChatRepository;)V", "invoke", "Lkotlinx/coroutines/flow/Flow;", "Lcom/example/chatflow/util/ResultState;", "", "Lcom/example/chatflow/domain/model/RecentChat;", "limit", "", "offset", "app_debug"})
public final class GetPagedChatsUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.example.chatflow.domain.repository.ChatRepository repo = null;
    
    public GetPagedChatsUseCase(@org.jetbrains.annotations.NotNull()
    com.example.chatflow.domain.repository.ChatRepository repo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.example.chatflow.util.ResultState<java.util.List<com.example.chatflow.domain.model.RecentChat>>> invoke(int limit, int offset) {
        return null;
    }
}