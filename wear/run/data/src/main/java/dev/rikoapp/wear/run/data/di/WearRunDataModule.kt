package dev.rikoapp.wear.run.data.di

import dev.rikoapp.wear.run.data.HealthServicesExerciseTracker
import dev.rikoapp.wear.run.domain.ExerciseTracker
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val wearRunDataModule = module {
    singleOf(::HealthServicesExerciseTracker).bind<ExerciseTracker>()
}