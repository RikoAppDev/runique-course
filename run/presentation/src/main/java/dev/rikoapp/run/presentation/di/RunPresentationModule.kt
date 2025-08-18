package dev.rikoapp.run.presentation.di

import dev.rikoapp.run.domain.RunningTracker
import dev.rikoapp.run.presentation.active_run.ActiveRunViewModel
import dev.rikoapp.run.presentation.run_overview.RunOverviewViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val runPresentationModule = module {
    singleOf(::RunningTracker)

    viewModelOf(::RunOverviewViewModel)
    viewModelOf(::ActiveRunViewModel)
}