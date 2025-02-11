package dev.rikoapp.run.location.di

import dev.rikoapp.run.domain.LocationObserver
import dev.rikoapp.run.location.AndroidLocationObserver
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val locationModule = module {
    singleOf(::AndroidLocationObserver).bind<LocationObserver>()
}