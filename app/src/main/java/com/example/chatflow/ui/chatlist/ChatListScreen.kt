package com.example.chatflow.ui.chatlist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.chatflow.domain.model.RecentChat
import com.example.chatflow.presentation.chatlist.ChatListViewModel
import com.example.chatflow.util.ResultState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ChatListScreen(
    onOpenChat: (Int) -> Unit,
    vm: ChatListViewModel = hiltViewModel(),
) {
    // VM state
    val chatsState by vm.chats.collectAsState()
    val isRefreshing by vm.isRefreshing.collectAsState()
    val isSyncing by vm.isSyncing.collectAsState()
    val apiTime by vm.apiTimeMs.collectAsState()
    val search by vm.search.collectAsState()
 
    
    // UI state
    val refreshState = rememberSwipeRefreshState(isRefreshing)
    val lazyListState = rememberLazyListState()
    val onOpenChatStable by rememberUpdatedState(onOpenChat)
    
    // Debounced search
    var localSearch by remember(search) { mutableStateOf(search) }
    val scope = rememberCoroutineScope()
    var searchJob by remember { mutableStateOf<Job?>(null) }
    fun updateSearchDebounced(text: String) {
        localSearch = text
        searchJob?.cancel()
        searchJob = scope.launch {
            delay(250)
            vm.setSearch(text)
        }
    }
    
    // Infinite scroll trigger (manual paging on top of ResultState list)
    val shouldLoadMore by remember {
        derivedStateOf {
            val last = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            val total = lazyListState.layoutInfo.totalItemsCount
            last != null && total >= 10 && last >= total - 2
        }
    }
    var loadingMore by remember { mutableStateOf(false) }
    
    // First load (no double trigger)
    LaunchedEffect(Unit) {
        if (!loadingMore) {
            loadingMore = true
            vm.loadMore()
        }
    }
    
    // Scroll-based paging (start spinner; stop only when state leaves Loading)
    LaunchedEffect(shouldLoadMore, isRefreshing) {
        if (shouldLoadMore && !isRefreshing && !loadingMore) {
            loadingMore = true
            vm.loadMore()
        }
    }
    
    // Close footer spinner only when ResultState leaves Loading
    LaunchedEffect(chatsState) {
        if (loadingMore && chatsState !is ResultState.Loading) {
            loadingMore = false
        }
    }
    
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            
            // Search
            SearchBox(
                value = localSearch,
                onValueChange = ::updateSearchDebounced,
                onSearch = { vm.setSearch(localSearch) },
                isLoading = isRefreshing || isSyncing,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            )
            
            // Thin top loader
            AnimatedVisibility(visible = isRefreshing || isSyncing) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                )
            }
            
            // Optional API time
            apiTime?.let {
                Text(
                    text = "API: ${it}ms",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
            
            SwipeRefresh(
                state = refreshState,
                onRefresh = { vm.refresh() }
            ) {
                when (val state = chatsState) {
                    is ResultState.Loading -> {
                        // Center spinner only for initial load (when no items yet)
                        val hasItems = false
                        if (!hasItems) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 48.dp),
                                contentAlignment = Alignment.TopCenter
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                    
                    is ResultState.Error -> {
                        Text(
                            text = state.message,
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    
                    is ResultState.Success -> {
                        val list: List<RecentChat> = state.data
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            state = lazyListState,
                            contentPadding = PaddingValues(vertical = 4.dp)
                        ) {
                            itemsIndexed(
                                items = list,
                                key = { _, item -> item.id },
                                contentType = { _, _ -> "chat" }
                            ) { _, chat ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                        .animateItemPlacement(animationSpec = spring())
                                ) {
                                    ChatItem(
                                        chat = chat,
                                        onClick = { onOpenChatStable(chat.id) }
                                    )
                                }
                            }
                            
                            // Footer spinner: show while loading more OR when state==Loading but list already visible
                            val showFooter = loadingMore ||
                                (chatsState is ResultState.Loading && list.isNotEmpty())
                            
                            if (showFooter) {
                                item("loadingMore") {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(12.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        CircularProgressIndicator(strokeWidth = 2.dp)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        // Full-screen overlay loader during API sync (pull-to-refresh)
        if (isSyncing) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 56.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBox(
    value: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    
    ElevatedCard(modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            placeholder = { Text("Search chats…") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search"
                )
            },
            trailingIcon = {
                when {
                    isLoading -> {
                        CircularProgressIndicator(
                            strokeWidth = 2.dp,
                            modifier = Modifier
                                .size(18.dp)
                                .padding(end = 2.dp)
                        )
                    }
                    value.isNotEmpty() -> {
                        IconButton(onClick = { onValueChange("") }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Clear"
                            )
                        }
                    }
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch()
                    focusManager.clearFocus()
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .padding(10.dp),
            shape = MaterialTheme.shapes.large
        )
    }
}
