package dev.rikoapp.core.data.di

import dev.rikoapp.core.data.auth.EncryptedSessionStorage
import dev.rikoapp.core.data.networking.HttpClientFactory
import dev.rikoapp.core.data.run.OfflineFirstRunRepository
import dev.rikoapp.core.domain.SessionStorage
import dev.rikoapp.core.domain.run.RunRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory(get()).build()
    }
    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()

    singleOf(::OfflineFirstRunRepository).bind<RunRepository>()
}