package dev.rikoapp.run.data.di

import dev.rikoapp.core.domain.run.SyncRunScheduler
import dev.rikoapp.run.data.CreateRunWorker
import dev.rikoapp.run.data.DeleteRunWorker
import dev.rikoapp.run.data.FetchRunsWorker
import dev.rikoapp.run.data.SyncRunWorkerScheduler
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val runDataModule = module {
    workerOf(::CreateRunWorker)
    workerOf(::FetchRunsWorker)
    workerOf(::DeleteRunWorker)

    singleOf(::SyncRunWorkerScheduler).bind<SyncRunScheduler>()
}