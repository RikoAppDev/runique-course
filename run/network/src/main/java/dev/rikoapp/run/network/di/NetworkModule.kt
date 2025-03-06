package dev.rikoapp.run.network.di

import dev.rikoapp.core.domain.run.RemoteRunDataSource
import dev.rikoapp.run.network.KtorRemoteRunDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {
    singleOf(::KtorRemoteRunDataSource).bind<RemoteRunDataSource>()
}