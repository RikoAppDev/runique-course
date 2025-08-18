package dev.rikoapp.analytics.presentation

sealed interface AnalyticsAction {
    data object OnBackClick : AnalyticsAction
}