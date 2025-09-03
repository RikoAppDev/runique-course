package dev.rikoapp.core.connectivity.data.di

import dev.rikoapp.core.connectivity.data.WearNodeDiscovery
import dev.rikoapp.core.connectivity.data.messaging.WearMessagingClient
import dev.rikoapp.core.connectivity.domain.NodeDiscovery
import dev.rikoapp.core.connectivity.domain.messaging.MessagingClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreConnectivityDataModule = module {
    singleOf(::WearNodeDiscovery).bind<NodeDiscovery>()
    singleOf(::WearMessagingClient).bind<MessagingClient>()
}