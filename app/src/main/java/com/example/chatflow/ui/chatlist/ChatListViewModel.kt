package com.example.chatflow.presentation.chatlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatflow.domain.model.RecentChat
import com.example.chatflow.domain.usecase.GetPagedChatsUseCase
import com.example.chatflow.domain.usecase.ObserveApiTimeMsUseCase
import com.example.chatflow.domain.usecase.ObserveCachedChatsUseCase
import com.example.chatflow.domain.usecase.SyncChatsOnceUseCase
import com.example.chatflow.util.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val getPagedChats: GetPagedChatsUseCase,
    private val observeCachedChats: ObserveCachedChatsUseCase,
    private val syncOnce: SyncChatsOnceUseCase,
    private val observeApiTimeMs: ObserveApiTimeMsUseCase
) : ViewModel() {
    
    // ---------- Public UI state ----------
    private val _search = MutableStateFlow("")
    val search: StateFlow<String> = _search.asStateFlow()
    
    // Paged list with loaders/errors (center+footer)
    private val _chats = MutableStateFlow<ResultState<List<RecentChat>>>(ResultState.Loading)
    val chats: StateFlow<ResultState<List<RecentChat>>> = _chats.asStateFlow()
    
    val apiTimeMs: StateFlow<Long?> =
        observeApiTimeMs()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)
    
    
    // Live cache (Room) -> instant list; apply search here
    val cachedFiltered: StateFlow<List<RecentChat>> =
        observeCachedChats()
            .combine(search) { list, q ->
                if (q.isBlank()) list
                else list.filter { it.name.contains(q, ignoreCase = true) }
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
    
    private val _isRefreshing = MutableStateFlow(false)     // pull-to-refresh spinner
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()
    
    private val _isSyncing = MutableStateFlow(false)        // optional overlay sync indicator
    val isSyncing: StateFlow<Boolean> = _isSyncing.asStateFlow()
    
    // ---------- Paging bookkeeping ----------
    private var offset = 0
    private val pageSize = 10
    private val accumulator = mutableListOf<RecentChat>()
    private var loadingPage = false
    
    // Footer loader job (delayed to avoid flicker)
    private var footerLoaderJob: Job? = null
    private val footerDelayMs = 1_000L
    
    init { resetAndLoadFirstPage() }
    
    // ---------- Intents ----------
    
    fun setSearch(q: String) {
        _search.value = q
        // For now we reset paging on search to keep UX predictable.
        resetAndLoadFirstPage()
    }
    
    fun refresh() {
        if (_isRefreshing.value) return
        viewModelScope.launch {
            // Optional overlay sync (network-only)
            _isSyncing.value = true
            runCatching { syncOnce() }
                .onFailure { Timber.w(it, "refresh(): syncOnce failed") }
            _isSyncing.value = false
            
            _isRefreshing.value = true
            try {
                resetPagingCounters()
                _chats.value = ResultState.Loading
                collectPage(limit = pageSize, offset = offset) {
                    offset += pageSize
                }
            } finally {
                _isRefreshing.value = false
            }
        }
    }
    
    fun loadMore() {
        if (loadingPage || _isRefreshing.value || _isSyncing.value) return
        loadingPage = true
        
        viewModelScope.launch(Dispatchers.IO) {
            Timber.d("loadMore(): request page offset=$offset size=$pageSize")
            
            if (accumulator.isNotEmpty()) {
                // Show footer loader only if the load is slow
                footerLoaderJob?.cancel()
                footerLoaderJob = launch {
                    delay(footerDelayMs)
                    collectPage(limit = pageSize, offset = offset) {
                        offset += pageSize
                        loadingPage = false
                    }
                }
            } else {
                // First page → center spinner immediately
                _chats.value = ResultState.Loading
                collectPage(limit = pageSize, offset = offset) {
                    offset += pageSize
                    loadingPage = false
                }
            }
        }
    }
    
    fun resetAndLoadFirstPage() {
        if (loadingPage || _isRefreshing.value || _isSyncing.value) return
        viewModelScope.launch {
            resetPagingCounters()
            _chats.value = ResultState.Loading
            collectPage(limit = pageSize, offset = 0) {
                offset += pageSize
            }
        }
    }
    
    // ---------- Helpers ----------
    
    private fun resetPagingCounters() {
        offset = 0
        accumulator.clear()
        loadingPage = false
        footerLoaderJob?.cancel()
        footerLoaderJob = null
    }
    
    /**
     * Collect a single page via use case.
     * - Initial page: propagate Loading for center spinner.
     * - Paged loads: footer loader is delayed; avoid overriding Success too early.
     */
    private suspend fun collectPage(
        limit: Int,
        offset: Int,
        onSuccessCommitted: () -> Unit
    ) {
        getPagedChats(limit, offset).collect { res ->
            when (res) {
                is ResultState.Loading -> {
                    if (accumulator.isEmpty()) {
                        _chats.value = ResultState.Loading
                    }
                }
                is ResultState.Success -> {
                    if (offset == 0) accumulator.clear()
                    accumulator.addAll(res.data)
                    // Optional: apply search on the combined list if server-side search not supported
                    val q = search.value
                    val finalList =
                        if (q.isBlank()) accumulator.toList()
                        else accumulator.filter { it.name.contains(q, ignoreCase = true) }
                    _chats.value = ResultState.Success(finalList)
                    onSuccessCommitted()
                }
                is ResultState.Error -> {
                    footerLoaderJob?.cancel()
                    footerLoaderJob = null
                    loadingPage = false
                    _chats.value = ResultState.Error(res.message)
                }
            }
        }
    }
    
    override fun onCleared() {
        super.onCleared()
        footerLoaderJob?.cancel()
    }
}
