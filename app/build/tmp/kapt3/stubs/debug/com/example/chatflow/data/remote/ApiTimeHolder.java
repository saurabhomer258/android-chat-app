package com.example.chatflow.data.remote;

import okhttp3.Interceptor;
import okhttp3.Response;
import timber.log.Timber;
import java.util.concurrent.TimeUnit;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/example/chatflow/data/remote/ApiTimeHolder;", "", "()V", "lastResponseTimeMs", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "getLastResponseTimeMs", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "app_debug"})
public final class ApiTimeHolder {
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Long> lastResponseTimeMs = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.chatflow.data.remote.ApiTimeHolder INSTANCE = null;
    
    private ApiTimeHolder() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Long> getLastResponseTimeMs() {
        return null;
    }
}