package dev.rikoapp.wear.run.presentation

sealed interface TrackerEvent {
    data object RunFinished : TrackerEvent
}