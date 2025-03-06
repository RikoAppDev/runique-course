package dev.rikoapp.runiquecourse

import android.app.Application
import dev.rikoapp.auth.data.di.authDataModule
import dev.rikoapp.auth.presentation.di.authViewModelModule
import dev.rikoapp.core.data.di.coreDataModule
import dev.rikoapp.core.database.di.databaseModule
import dev.rikoapp.run.location.di.locationModule
import dev.rikoapp.run.network.di.networkModule
import dev.rikoapp.run.presentation.di.runPresentationModule
import dev.rikoapp.runiquecourse.di.appModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class RuniqueCourseApp : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@RuniqueCourseApp)
            modules(
                authDataModule,
                authViewModelModule,
                appModule,
                coreDataModule,
                runPresentationModule,
                locationModule,
                databaseModule,
                networkModule
            )
        }
    }
}