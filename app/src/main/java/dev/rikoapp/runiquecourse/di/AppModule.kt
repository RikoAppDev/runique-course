package dev.rikoapp.runiquecourse.di

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dev.rikoapp.runiquecourse.MainViewModel
import dev.rikoapp.runiquecourse.RuniqueCourseApp
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<SharedPreferences> {
        try {
            EncryptedSharedPreferences(
                androidApplication(),
                "auth_prefs",
                MasterKey(androidApplication()),
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } catch (_: Exception) {
            // If something goes wrong (e.g. AEADBadTagException), fallback to clearing the corrupted file
            androidApplication().deleteSharedPreferences("auth_prefs")

            EncryptedSharedPreferences(
                androidApplication(),
                "auth_prefs",
                MasterKey(androidApplication()),
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        }
    }
    single<CoroutineScope> {
        (androidApplication() as RuniqueCourseApp).applicationScope
    }

    viewModelOf(::MainViewModel)
}