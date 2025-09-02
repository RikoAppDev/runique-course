package dev.rikoapp.wear.app.presentation

import android.app.Application
import dev.rikoapp.core.connectivity.data.di.coreConnectivityDataModule
import dev.rikoapp.wear.app.presentation.di.appModule
import dev.rikoapp.wear.run.data.di.wearRunDataModule
import dev.rikoapp.wear.run.presentation.di.wearRunPresentationModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RuniqueCourseApp : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@RuniqueCourseApp)
            modules(
                appModule,
                wearRunPresentationModule,
                wearRunDataModule,
                coreConnectivityDataModule
            )
        }
    }
}