package dev.rikoapp.core.connectivity.domain.messaging

import dev.rikoapp.core.domain.util.EmptyDataResult
import kotlinx.coroutines.flow.Flow

interface MessagingClient {
    fun connectToNode(nodeId: String): Flow<MessagingAction>
    suspend fun sendOrQueueAction(action: MessagingAction): EmptyDataResult<MessagingError>
}