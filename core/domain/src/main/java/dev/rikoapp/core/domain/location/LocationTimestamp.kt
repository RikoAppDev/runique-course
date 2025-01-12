package dev.rikoapp.core.domain.location

import kotlin.time.Duration

data class LocationTimestamp(
    val locationWithAltitude: LocationWithAltitude,
    val durationTimestamp: Duration
)
