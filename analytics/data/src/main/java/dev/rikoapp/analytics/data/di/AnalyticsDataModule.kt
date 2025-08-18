package dev.rikoapp.analytics.data.di

import dev.rikoapp.analytics.data.RoomAnalyticsRepository
import dev.rikoapp.analytics.domain.AnalyticsRepository
import dev.rikoapp.core.database.RunDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val analyticsModule = module {
    singleOf(::RoomAnalyticsRepository).bind<AnalyticsRepository>()
    single {
        get<RunDatabase>().analyticsDao
    }
}