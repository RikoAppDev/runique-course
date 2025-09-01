package dev.rikoapp.wear.run.presentation.di

import dev.rikoapp.wear.run.presentation.TrackerViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val runPresentationModule = module {
    viewModelOf(::TrackerViewModel)
}