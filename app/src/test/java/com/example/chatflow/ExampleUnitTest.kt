package com.example.chatflow.presentation.chatlist

import app.cash.turbine.ReceiveTurbine
import app.cash.turbine.test
import com.example.chatflow.domain.model.RecentChat
import com.example.chatflow.domain.usecase.GetPagedChatsUseCase
import com.example.chatflow.domain.usecase.ObserveApiTimeMsUseCase
import com.example.chatflow.domain.usecase.ObserveCachedChatsUseCase
import com.example.chatflow.domain.usecase.SyncChatsOnceUseCase
import com.example.chatflow.presentation.chatlist.ChatListViewModel
import com.example.chatflow.util.ResultState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ChatListViewModelTest {
    
    private val dispatcher = StandardTestDispatcher()
    
    private val getPaged: GetPagedChatsUseCase = mockk(relaxed = true)
    private val observeCached: ObserveCachedChatsUseCase = mockk(relaxed = true)
    private val syncOnce: SyncChatsOnceUseCase = mockk(relaxed = true)
    private val observeApiTimeMs: ObserveApiTimeMsUseCase = mockk(relaxed = true)
    
    // Backing flows
    private val cachedFlow = MutableStateFlow<List<RecentChat>>(emptyList())
    private val apiTimeFlow = MutableStateFlow<Long?>(null)
    
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        every { observeCached.invoke() } returns cachedFlow
        every { observeApiTimeMs.invoke() } returns apiTimeFlow
    }
    
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    
    // --- helper: skip any Loading and return next Success
    private suspend fun ReceiveTurbine<ResultState<List<RecentChat>>>.awaitNextSuccess()
        : ResultState.Success<List<RecentChat>> {
        while (true) {
            when (val v = awaitItem()) {
                is ResultState.Success -> return v
                is ResultState.Error   -> error("Expected Success, got Error: ${v.message}")
                is ResultState.Loading -> Unit
            }
        }
    }
    
    private fun fakePage(startId: Int, count: Int) =
        (0 until count).map { i ->
            RecentChat(
                id = startId + i,
                name = "User ${startId + i}",
                avatarUrl = null,
                lastMessage = "Hi",
                unreadCount = 0,
                lastSeen = 0L,
                isOnline = false
            )
        }
    
    @Test
    fun initial_load_emits_loading_then_success() = runTest(dispatcher) {
        coEvery { getPaged.invoke(10, 0) } returns flow {
            emit(ResultState.Loading)
            emit(ResultState.Success(fakePage(1, 10)))
        }
        
        val vm = ChatListViewModel(getPaged, observeCached, syncOnce, observeApiTimeMs)
        
        vm.chats.test {
            assertEquals(ResultState.Loading, awaitItem())
            val s = awaitNextSuccess()
            assertEquals(10, s.data.size)
            assertEquals(1, s.data.first().id)
            cancelAndIgnoreRemainingEvents()
        }
    }
    
    @Test
    fun load_more_appends_page() = runTest(dispatcher) {
        coEvery { getPaged.invoke(10, 0) } returns flow {
            emit(ResultState.Loading)
            emit(ResultState.Success(fakePage(1, 10)))
        }
        coEvery { getPaged.invoke(10, 10) } returns flow {
            emit(ResultState.Success(fakePage(11, 10)))
        }
        
        val vm = ChatListViewModel(getPaged, observeCached, syncOnce, observeApiTimeMs)
        
        vm.chats.test {
            assertEquals(ResultState.Loading, awaitItem())
            val first = awaitNextSuccess()
            assertEquals(10, first.data.size)
            
            vm.loadMore()
            advanceUntilIdle()
            
            val second = awaitNextSuccess()
            assertEquals(20, second.data.size)
            assertEquals(11, second.data[10].id)
            cancelAndIgnoreRemainingEvents()
        }
    }
    
    @Test
    fun refresh_calls_syncOnce_and_reloads_first_page() = runTest(dispatcher) {
        var page0Loads = 0
        coEvery { getPaged.invoke(10, 0) } returns flow {
            page0Loads++
            emit(ResultState.Loading)
            val start = if (page0Loads == 1) 1 else 101
            emit(ResultState.Success(fakePage(start, 10)))
        }
        coEvery { getPaged.invoke(10, 10) } returns flow { emit(ResultState.Success(emptyList())) }
        coEvery { syncOnce.invoke() } returns Unit
        
        val vm = ChatListViewModel(getPaged, observeCached, syncOnce, observeApiTimeMs)
        
        vm.chats.test {
            assertEquals(ResultState.Loading, awaitItem())
            val first = awaitNextSuccess()
            assertEquals(1, first.data.first().id)
            
            vm.refresh()
            advanceUntilIdle()
            
            coVerify(exactly = 1) { syncOnce.invoke() }
            
            val second = awaitNextSuccess()
            assertEquals(101, second.data.first().id)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
