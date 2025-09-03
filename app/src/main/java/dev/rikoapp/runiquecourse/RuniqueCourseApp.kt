package dev.rikoapp.runiquecourse

import android.app.Application
import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompat
import dev.rikoapp.auth.data.di.authDataModule
import dev.rikoapp.auth.presentation.di.authViewModelModule
import dev.rikoapp.core.connectivity.data.di.coreConnectivityDataModule
import dev.rikoapp.core.data.di.coreDataModule
import dev.rikoapp.core.database.di.databaseModule
import dev.rikoapp.run.data.di.runDataModule
import dev.rikoapp.run.location.di.locationModule
import dev.rikoapp.run.network.di.networkModule
import dev.rikoapp.run.presentation.di.runPresentationModule
import dev.rikoapp.runiquecourse.di.appModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
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
            workManagerFactory()
            modules(
                authDataModule,
                authViewModelModule,
                appModule,
                coreDataModule,
                runPresentationModule,
                locationModule,
                databaseModule,
                networkModule,
                runDataModule,
                coreConnectivityDataModule
            )
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
    }
}