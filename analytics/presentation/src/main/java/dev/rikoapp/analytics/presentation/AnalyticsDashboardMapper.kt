package dev.rikoapp.analytics.presentation

import dev.rikoapp.analytics.domain.AnalyticsValues
import dev.rikoapp.presentation.ui.formated
import dev.rikoapp.presentation.ui.toFormattedHeartRate
import dev.rikoapp.presentation.ui.toFormattedKm
import dev.rikoapp.presentation.ui.toFormattedKmh
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

fun Duration.toFormatedTotalTime(): String {
    val days = toLong(DurationUnit.DAYS)
    val hours = toLong(DurationUnit.HOURS) % 24
    val minutes = toLong(DurationUnit.MINUTES) % 60

    return "${days}d ${hours}h ${minutes}m"
}

fun AnalyticsValues.toAnalyticsDashboardState(): AnalyticsDashboardState {
    return AnalyticsDashboardState(
        totalDistanceRun = (totalDistanceRun / 1000.0).toFormattedKm(),
        totalTimeRun = totalTimeRun.toFormatedTotalTime(),
        fastestEverRun = fastestEverRun.toFormattedKmh(),
        avgDistance = (avgDistancePerRun / 1000.0).toFormattedKm(),
        avgPace = avgPacePerRun.seconds.formated(),
        avgHeartRate = avgHeartRate.toFormattedHeartRate(),
        maxHeartRate = maxHeartRate.toFormattedHeartRate()
    )
}