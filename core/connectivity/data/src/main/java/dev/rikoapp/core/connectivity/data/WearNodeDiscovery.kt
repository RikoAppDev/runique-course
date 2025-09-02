package dev.rikoapp.core.connectivity.data

import android.content.Context
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.wearable.CapabilityClient
import com.google.android.gms.wearable.Wearable
import dev.rikoapp.core.connectivity.domain.DeviceNode
import dev.rikoapp.core.connectivity.domain.DeviceType
import dev.rikoapp.core.connectivity.domain.NodeDiscovery
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class WearNodeDiscovery(
    context: Context
) : NodeDiscovery {

    private val capabilityClient = Wearable.getCapabilityClient(context)

    override fun observeConnectedDevices(localDeviceType: DeviceType): Flow<Set<DeviceNode>> {
        return callbackFlow {
            val remoteCapability = when (localDeviceType) {
                DeviceType.PHONE -> "runique_course_wear_app"
                DeviceType.WATCH -> "runique_course_phone_app"
            }
            try {
                val capability = capabilityClient
                    .getCapability(remoteCapability, CapabilityClient.FILTER_REACHABLE)
                    .await()
                val connectedDevices = capability.nodes.map { it.toDeviceNode() }.toSet()
                send(connectedDevices)
            } catch (_: ApiException) {
                awaitClose()
                return@callbackFlow
            }

            val listener = CapabilityClient.OnCapabilityChangedListener { capability ->
                val connectedDevices = capability.nodes.map { it.toDeviceNode() }.toSet()
                trySend(connectedDevices)
            }
            capabilityClient.addListener(listener, remoteCapability)

            awaitClose {
                capabilityClient.removeListener(listener)
            }
        }
    }
}