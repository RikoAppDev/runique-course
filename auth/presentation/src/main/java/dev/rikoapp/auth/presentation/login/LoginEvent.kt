package dev.rikoapp.auth.presentation.login

import dev.rikoapp.presentation.ui.UiText

sealed interface LoginEvent {
    data object LoginSuccess : LoginEvent
    data class Error(val error: UiText) : LoginEvent
}
