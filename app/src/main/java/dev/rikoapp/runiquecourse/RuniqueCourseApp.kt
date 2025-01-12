package dev.rikoapp.runiquecourse

import android.app.Application
import dev.rikoapp.auth.data.di.authDataModule
import dev.rikoapp.auth.presentation.di.authViewModelModule
import dev.rikoapp.core.data.di.coreDataModule
import dev.rikoapp.run.presentation.di.runViewModelModule
import dev.rikoapp.runiquecourse.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class RuniqueCourseApp : Application() {
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
                runViewModelModule
            )
        }
    }
}