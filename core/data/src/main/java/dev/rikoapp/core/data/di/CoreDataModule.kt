package dev.rikoapp.core.data.di

import dev.rikoapp.core.data.auth.EncryptedSessionStorage
import dev.rikoapp.core.data.networking.HttpClientFactory
import dev.rikoapp.core.domain.SessionStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory().build()
    }
    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
}