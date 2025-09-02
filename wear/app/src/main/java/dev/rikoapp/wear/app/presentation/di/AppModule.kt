package dev.rikoapp.wear.app.presentation.di

import dev.rikoapp.wear.app.presentation.RuniqueCourseApp
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single {
        (androidApplication() as RuniqueCourseApp).applicationScope
    }
}