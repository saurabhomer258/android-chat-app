package com.example.chatflow.presentation.chatlist;

import androidx.lifecycle.ViewModel;
import com.example.chatflow.domain.model.RecentChat;
import com.example.chatflow.domain.usecase.GetPagedChatsUseCase;
import com.example.chatflow.domain.usecase.ObserveApiTimeMsUseCase;
import com.example.chatflow.domain.usecase.ObserveCachedChatsUseCase;
import com.example.chatflow.domain.usecase.SyncChatsOnceUseCase;
import com.example.chatflow.util.ResultState;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import timber.log.Timber;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ,\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\'2\u0006\u0010&\u001a\u00020\'2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020,0/H\u0082@\u00a2\u0006\u0002\u00100J\u0006\u00101\u001a\u00020,J\b\u00102\u001a\u00020,H\u0014J\u0006\u00103\u001a\u00020,J\u0006\u00104\u001a\u00020,J\b\u00105\u001a\u00020,H\u0002J\u000e\u00106\u001a\u00020,2\u0006\u00107\u001a\u00020\u0014R \u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR#\u0010\u001e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\r0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001bR\u000e\u0010 \u001a\u00020\u0019X\u0082D\u00a2\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00110\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001bR\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00110\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001bR\u000e\u0010%\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\'X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\'X\u0082D\u00a2\u0006\u0002\n\u0000R\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00140\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00068"}, d2 = {"Lcom/example/chatflow/presentation/chatlist/ChatListViewModel;", "Landroidx/lifecycle/ViewModel;", "getPagedChats", "Lcom/example/chatflow/domain/usecase/GetPagedChatsUseCase;", "observeCachedChats", "Lcom/example/chatflow/domain/usecase/ObserveCachedChatsUseCase;", "syncOnce", "Lcom/example/chatflow/domain/usecase/SyncChatsOnceUseCase;", "observeApiTimeMs", "Lcom/example/chatflow/domain/usecase/ObserveApiTimeMsUseCase;", "(Lcom/example/chatflow/domain/usecase/GetPagedChatsUseCase;Lcom/example/chatflow/domain/usecase/ObserveCachedChatsUseCase;Lcom/example/chatflow/domain/usecase/SyncChatsOnceUseCase;Lcom/example/chatflow/domain/usecase/ObserveApiTimeMsUseCase;)V", "_chats", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/chatflow/util/ResultState;", "", "Lcom/example/chatflow/domain/model/RecentChat;", "_isRefreshing", "", "_isSyncing", "_search", "", "accumulator", "", "apiTimeMs", "Lkotlinx/coroutines/flow/StateFlow;", "", "getApiTimeMs", "()Lkotlinx/coroutines/flow/StateFlow;", "cachedFiltered", "getCachedFiltered", "chats", "getChats", "footerDelayMs", "footerLoaderJob", "Lkotlinx/coroutines/Job;", "isRefreshing", "isSyncing", "loadingPage", "offset", "", "pageSize", "search", "getSearch", "collectPage", "", "limit", "onSuccessCommitted", "Lkotlin/Function0;", "(IILkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadMore", "onCleared", "refresh", "resetAndLoadFirstPage", "resetPagingCounters", "setSearch", "q", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ChatListViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.chatflow.domain.usecase.GetPagedChatsUseCase getPagedChats = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.chatflow.domain.usecase.ObserveCachedChatsUseCase observeCachedChats = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.chatflow.domain.usecase.SyncChatsOnceUseCase syncOnce = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.chatflow.domain.usecase.ObserveApiTimeMsUseCase observeApiTimeMs = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _search = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> search = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.chatflow.util.ResultState<java.util.List<com.example.chatflow.domain.model.RecentChat>>> _chats = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.chatflow.util.ResultState<java.util.List<com.example.chatflow.domain.model.RecentChat>>> chats = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Long> apiTimeMs = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.chatflow.domain.model.RecentChat>> cachedFiltered = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isRefreshing = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isRefreshing = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isSyncing = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isSyncing = null;
    private int offset = 0;
    private final int pageSize = 10;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.chatflow.domain.model.RecentChat> accumulator = null;
    private boolean loadingPage = false;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job footerLoaderJob;
    private final long footerDelayMs = 1000L;
    
    @javax.inject.Inject()
    public ChatListViewModel(@org.jetbrains.annotations.NotNull()
    com.example.chatflow.domain.usecase.GetPagedChatsUseCase getPagedChats, @org.jetbrains.annotations.NotNull()
    com.example.chatflow.domain.usecase.ObserveCachedChatsUseCase observeCachedChats, @org.jetbrains.annotations.NotNull()
    com.example.chatflow.domain.usecase.SyncChatsOnceUseCase syncOnce, @org.jetbrains.annotations.NotNull()
    com.example.chatflow.domain.usecase.ObserveApiTimeMsUseCase observeApiTimeMs) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getSearch() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.chatflow.util.ResultState<java.util.List<com.example.chatflow.domain.model.RecentChat>>> getChats() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Long> getApiTimeMs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.chatflow.domain.model.RecentChat>> getCachedFiltered() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isRefreshing() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isSyncing() {
        return null;
    }
    
    public final void setSearch(@org.jetbrains.annotations.NotNull()
    java.lang.String q) {
    }
    
    public final void refresh() {
    }
    
    public final void loadMore() {
    }
    
    public final void resetAndLoadFirstPage() {
    }
    
    private final void resetPagingCounters() {
    }
    
    /**
     * Collect a single page via use case.
     * - Initial page: propagate Loading for center spinner.
     * - Paged loads: footer loader is delayed; avoid overriding Success too early.
     */
    private final java.lang.Object collectPage(int limit, int offset, kotlin.jvm.functions.Function0<kotlin.Unit> onSuccessCommitted, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}