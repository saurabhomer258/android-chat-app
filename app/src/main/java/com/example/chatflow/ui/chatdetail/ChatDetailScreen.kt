package com.example.chatflow.ui.chatdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatDetailScreen(userId: Int) {
    
    // Fake messages for now (replace with ViewModel later)
    val messages = remember {
        List(20) { index ->
            if (index % 2 == 0) Message("User $userId", "Message $index", false)
            else Message("You", "Reply $index", true)
        }
    }
    
    val lazyListState = rememberLazyListState()
    
    // Auto-scroll to bottom when opened
    LaunchedEffect(messages.size) {
        lazyListState.animateScrollToItem(messages.lastIndex)
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chat with $userId") },
            )
        },
        bottomBar = {
            var text by remember { mutableStateOf("") }
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    placeholder = { Text("Type a message…") },
                    modifier = Modifier.weight(1f),
                    shape = MaterialTheme.shapes.large,
                    maxLines = 4
                )
                Spacer(Modifier.width(8.dp))
                IconButton(
                    onClick = {
                        if (text.isNotBlank()) {
                            // TODO: vm.sendMessage()
                            text = ""
                        }
                    }
                ) {
                    Icon(Icons.Default.Send, contentDescription = "Send")
                }
            }
        }
    ) { padding ->
        
        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(12.dp)
        ) {
            
            itemsIndexed(messages) { _, msg ->
                ChatBubble(
                    text = msg.message,
                    isMe = msg.isMe
                )
                Spacer(Modifier.height(6.dp))
            }
            
            // Bottom spacer so last message isn't cut by input bar
            item { Spacer(Modifier.height(60.dp)) }
        }
    }
}

@Composable
private fun ChatBubble(text: String, isMe: Boolean) {
    val bubbleColor = if (isMe) MaterialTheme.colorScheme.primary else Color(0xFFEDEDED)
    val textColor = if (isMe) MaterialTheme.colorScheme.onPrimary else Color.Black
    val alignment = if (isMe) Alignment.End else Alignment.Start
    
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isMe) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .background(bubbleColor)
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .widthIn(max = 280.dp)
        ) {
            Text(
                text = text,
                color = textColor,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Visible
            )
        }
    }
}

data class Message(
    val sender: String,
    val message: String,
    val isMe: Boolean
)
