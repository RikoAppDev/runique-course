package dev.rikoapp.auth.presentation.di

import dev.rikoapp.auth.presentation.login.LoginViewModel
import dev.rikoapp.auth.presentation.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authViewModelModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
}