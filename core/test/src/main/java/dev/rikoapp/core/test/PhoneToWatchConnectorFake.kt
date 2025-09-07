package dev.rikoapp.core.test

import dev.rikoapp.core.connectivity.domain.DeviceNode
import dev.rikoapp.core.connectivity.domain.messaging.MessagingAction
import dev.rikoapp.core.connectivity.domain.messaging.MessagingError
import dev.rikoapp.core.domain.util.EmptyDataResult
import dev.rikoapp.core.domain.util.Result
import dev.rikoapp.run.domain.WatchConnector
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class PhoneToWatchConnectorFake : WatchConnector {

    var sendError: MessagingError? = null

    private val _isTrackable = MutableStateFlow(true)

    private val _connectedDevice = MutableStateFlow<DeviceNode?>(null)
    override val connectedDevice: StateFlow<DeviceNode?>
        get() = _connectedDevice.asStateFlow()

    private val _messagingActions = MutableSharedFlow<MessagingAction>()

    override val messagingActions: Flow<MessagingAction>
        get() = _messagingActions.asSharedFlow()

    override suspend fun sendActionToWatch(action: MessagingAction): EmptyDataResult<MessagingError> {
        return if (sendError == null) {
            Result.Success(Unit)
        } else {
            Result.Error(sendError!!)
        }
    }

    override fun setIsTrackable(isTrackable: Boolean) {
        _isTrackable.value = isTrackable
    }

    suspend fun sendFromWatchToPhone(action: MessagingAction) {
        _messagingActions.emit(action)
    }
}