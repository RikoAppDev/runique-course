package dev.rikoapp.auth.presentation.intro

sealed interface IntroAction {
    data object OnSignInClick : IntroAction
    data object OnSignUpClick : IntroAction
}