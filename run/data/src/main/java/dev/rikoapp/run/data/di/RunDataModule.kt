package dev.rikoapp.run.data.di

import dev.rikoapp.run.data.CreateRunWorker
import dev.rikoapp.run.data.DeleteRunWorker
import dev.rikoapp.run.data.FetchRunsWorker
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.dsl.module

val runDataModule = module {
    workerOf(::CreateRunWorker)
    workerOf(::FetchRunsWorker)
    workerOf(::DeleteRunWorker)
}