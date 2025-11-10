package com.example.chatflow.presentation.chatlist;

import app.cash.turbine.ReceiveTurbine;
import com.example.chatflow.domain.model.RecentChat;
import com.example.chatflow.domain.usecase.GetPagedChatsUseCase;
import com.example.chatflow.domain.usecase.ObserveApiTimeMsUseCase;
import com.example.chatflow.domain.usecase.ObserveCachedChatsUseCase;
import com.example.chatflow.domain.usecase.SyncChatsOnceUseCase;
import com.example.chatflow.presentation.chatlist.ChatListViewModel;
import com.example.chatflow.util.ResultState;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0002J\f\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019H\u0007J\f\u0010\u001a\u001a\u00060\u0018j\u0002`\u0019H\u0007J\f\u0010\u001b\u001a\u00060\u0018j\u0002`\u0019H\u0007J\b\u0010\u001c\u001a\u00020\u0018H\u0007J\b\u0010\u001d\u001a\u00020\u0018H\u0007J0\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u001f*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070!0 H\u0082@\u00a2\u0006\u0002\u0010\"R\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/example/chatflow/presentation/chatlist/ChatListViewModelTest;", "", "()V", "apiTimeFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "cachedFlow", "", "Lcom/example/chatflow/domain/model/RecentChat;", "dispatcher", "Lkotlinx/coroutines/test/TestDispatcher;", "getPaged", "Lcom/example/chatflow/domain/usecase/GetPagedChatsUseCase;", "observeApiTimeMs", "Lcom/example/chatflow/domain/usecase/ObserveApiTimeMsUseCase;", "observeCached", "Lcom/example/chatflow/domain/usecase/ObserveCachedChatsUseCase;", "syncOnce", "Lcom/example/chatflow/domain/usecase/SyncChatsOnceUseCase;", "fakePage", "startId", "", "count", "initial_load_emits_loading_then_success", "", "Lkotlinx/coroutines/test/TestResult;", "load_more_appends_page", "refresh_calls_syncOnce_and_reloads_first_page", "setUp", "tearDown", "awaitNextSuccess", "Lcom/example/chatflow/util/ResultState$Success;", "Lapp/cash/turbine/ReceiveTurbine;", "Lcom/example/chatflow/util/ResultState;", "(Lapp/cash/turbine/ReceiveTurbine;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debugUnitTest"})
@kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
public final class ChatListViewModelTest {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.test.TestDispatcher dispatcher = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.chatflow.domain.usecase.GetPagedChatsUseCase getPaged = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.chatflow.domain.usecase.ObserveCachedChatsUseCase observeCached = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.chatflow.domain.usecase.SyncChatsOnceUseCase syncOnce = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.chatflow.domain.usecase.ObserveApiTimeMsUseCase observeApiTimeMs = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.chatflow.domain.model.RecentChat>> cachedFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Long> apiTimeFlow = null;
    
    public ChatListViewModelTest() {
        super();
    }
    
    @org.junit.Before()
    public final void setUp() {
    }
    
    @org.junit.After()
    public final void tearDown() {
    }
    
    private final java.lang.Object awaitNextSuccess(app.cash.turbine.ReceiveTurbine<com.example.chatflow.util.ResultState<java.util.List<com.example.chatflow.domain.model.RecentChat>>> $this$awaitNextSuccess, kotlin.coroutines.Continuation<? super com.example.chatflow.util.ResultState.Success<? extends java.util.List<com.example.chatflow.domain.model.RecentChat>>> $completion) {
        return null;
    }
    
    private final java.util.List<com.example.chatflow.domain.model.RecentChat> fakePage(int startId, int count) {
        return null;
    }
    
    @org.junit.Test()
    public final void initial_load_emits_loading_then_success() {
    }
    
    @org.junit.Test()
    public final void load_more_appends_page() {
    }
    
    @org.junit.Test()
    public final void refresh_calls_syncOnce_and_reloads_first_page() {
    }
}