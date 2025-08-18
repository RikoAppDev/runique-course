package dev.rikoapp.analytics.data.di

import dev.rikoapp.analytics.data.RoomAnalyticsRepository
import dev.rikoapp.analytics.domain.AnalyticsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val analyticsModule = module {
    singleOf(::RoomAnalyticsRepository).bind<AnalyticsRepository>()
}