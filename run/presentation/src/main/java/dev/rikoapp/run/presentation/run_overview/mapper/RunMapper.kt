package dev.rikoapp.run.presentation.run_overview.mapper

import dev.rikoapp.core.domain.run.Run
import dev.rikoapp.presentation.ui.formated
import dev.rikoapp.presentation.ui.toFormattedKm
import dev.rikoapp.presentation.ui.toFormattedKmh
import dev.rikoapp.presentation.ui.toFormattedMeters
import dev.rikoapp.presentation.ui.toFormattedPace
import dev.rikoapp.run.presentation.run_overview.model.RunUi
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Run.toRunUi(): RunUi {
    val dateTimeInLocalTime = dateTimeUtc
        .withZoneSameInstant(ZoneId.systemDefault())
    val formattedDateTime = DateTimeFormatter
        .ofPattern("MMM dd, yyyy - hh:mma")
        .format(dateTimeInLocalTime)

    val distanceKm = distanceMeters / 1000.0

    return RunUi(
        id = id!!,
        duration = duration.formated(),
        dateTime = formattedDateTime,
        distance = distanceKm.toFormattedKm(),
        avgSpeed = avgSpeedKmh.toFormattedKmh(),
        maxSpeed = maxSpeedKmh.toFormattedKmh(),
        pace = duration.toFormattedPace(distanceKm),
        totalElevation = totalElevationMeters.toFormattedMeters(),
        mapPictureUrl = mapPictureUrl
    )
}