package dev.rikoapp.wear.app.presentation

import android.app.Application
import dev.rikoapp.wear.run.data.di.wearRunDataModule
import dev.rikoapp.wear.run.presentation.di.wearRunPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RuniqueCourseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@RuniqueCourseApp)
            modules(
                wearRunPresentationModule,
                wearRunDataModule
            )
        }
    }
}