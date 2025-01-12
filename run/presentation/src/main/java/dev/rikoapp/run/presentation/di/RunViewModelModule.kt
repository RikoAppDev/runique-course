package dev.rikoapp.run.presentation.di

import dev.rikoapp.run.presentation.active_run.ActiveRunViewModel
import dev.rikoapp.run.presentation.run_overview.RunOverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val runViewModelModule = module {
    viewModelOf(::RunOverviewViewModel)
    viewModelOf(::ActiveRunViewModel)
}