package com.example.chatflow.ui.chatlist

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.chatflow.domain.model.RecentChat
import com.example.chatflow.util.TimeUtils

@Composable
fun ChatItem(
    chat: RecentChat,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val onClickStable by rememberUpdatedState(onClick)
    val secondary = MaterialTheme.colorScheme.onSurfaceVariant
    
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClickStable)
            .padding(horizontal = 12.dp, vertical = 10.dp)
            .animateContentSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar + unread badge
        Box(modifier = Modifier.size(56.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(chat.avatarUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "${chat.name} avatar",
                modifier = Modifier
                    .matchParentSize()
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
            )
            
            if (chat.unreadCount > 0) {
                Badge(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = 4.dp, y = (4).dp)
                ) {
                    Text("${chat.unreadCount}")
                }
            }
        }
        
        Spacer(Modifier.width(12.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = chat.name,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = TimeUtils.formatTimeAgo(chat.lastSeen),
                    style = MaterialTheme.typography.labelSmall,
                    color = secondary
                )
            }
            
            Spacer(Modifier.height(2.dp))
            
            // Lightweight typing / last message (avoid double AnimatedVisibility re-measure)
            if (chat.isTyping) {
                Text(
                    text = "Typing…",
                    style = MaterialTheme.typography.bodySmall,
                    color = secondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            } else {
                Text(
                    text = chat.lastMessage,
                    style = MaterialTheme.typography.bodySmall,
                    color = secondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.semantics {
                        // accessibility hint
                        contentDescription = "Last message ${chat.lastMessage}"
                    }
                )
            }
        }
    }
}
