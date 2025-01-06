package dev.rikoapp.auth.data.di

import dev.rikoapp.auth.data.EmailPatternValidator
import dev.rikoapp.auth.domain.PatternValidator
import dev.rikoapp.auth.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authDataModule = module {
    single<PatternValidator> {
        EmailPatternValidator
    }
    singleOf(::UserDataValidator)
}