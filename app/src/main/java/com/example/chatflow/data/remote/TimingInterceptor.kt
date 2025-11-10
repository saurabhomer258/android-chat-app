package com.example.chatflow.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.TimeUnit

object ApiTimeHolder {
    val lastResponseTimeMs = MutableStateFlow<Long?>(null)
}

class TimingInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()
        val start = System.nanoTime()
        val resp = chain.proceed(req)
        val end = System.nanoTime()
        val tookMs = TimeUnit.NANOSECONDS.toMillis(end - start)
        ApiTimeHolder.lastResponseTimeMs.value = tookMs
        Timber.d("${req.url} -> ${tookMs}ms")
        return resp
    }
}
