package dev.rikoapp.wear.run.data

import dev.rikoapp.core.connectivity.domain.DeviceNode
import dev.rikoapp.core.connectivity.domain.DeviceType
import dev.rikoapp.core.connectivity.domain.NodeDiscovery
import dev.rikoapp.core.connectivity.domain.messaging.MessagingAction
import dev.rikoapp.core.connectivity.domain.messaging.MessagingClient
import dev.rikoapp.core.connectivity.domain.messaging.MessagingError
import dev.rikoapp.core.domain.util.EmptyDataResult
import dev.rikoapp.wear.run.domain.PhoneConnector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.shareIn

@OptIn(ExperimentalCoroutinesApi::class)
class WatchToPhoneConnector(
    nodeDiscovery: NodeDiscovery,
    applicationScope: CoroutineScope,
    private val messagingClient: MessagingClient
) : PhoneConnector {

    private val _connectedNode = MutableStateFlow<DeviceNode?>(null)
    override val connectedNode = _connectedNode.asStateFlow()

    override val messagingActions = nodeDiscovery
        .observeConnectedDevices(DeviceType.WATCH)
        .flatMapLatest { connectedNodes ->
            val node = connectedNodes.firstOrNull()

            if (node != null && node.isNearby) {
                _connectedNode.value = node
                messagingClient.connectToNode(node.id)
            } else flowOf()
        }
        .shareIn(
            applicationScope,
            started = SharingStarted.Eagerly
        )

    override suspend fun sendActionToPhone(action: MessagingAction): EmptyDataResult<MessagingError> {
        return messagingClient.sendOrQueueAction(action)
    }
}