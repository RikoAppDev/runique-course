package dev.rikoapp.wear.run.domain

import dev.rikoapp.core.connectivity.domain.DeviceNode
import dev.rikoapp.core.connectivity.domain.messaging.MessagingAction
import dev.rikoapp.core.connectivity.domain.messaging.MessagingError
import dev.rikoapp.core.domain.util.EmptyDataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface PhoneConnector {
    val connectedNode: StateFlow<DeviceNode?>
    val messagingActions: Flow<MessagingAction>

    suspend fun sendActionToPhone(action: MessagingAction): EmptyDataResult<MessagingError>
}