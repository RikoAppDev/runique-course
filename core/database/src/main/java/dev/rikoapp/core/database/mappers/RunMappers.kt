package dev.rikoapp.core.database.mappers

import dev.rikoapp.core.database.entity.RunEntity
import dev.rikoapp.core.domain.location.Location
import dev.rikoapp.core.domain.run.Run
import org.bson.types.ObjectId
import java.time.Instant
import java.time.ZoneId
import kotlin.time.Duration.Companion.milliseconds

fun RunEntity.toRun() = Run(
    id = id,
    duration = durationMillis.milliseconds,
    dateTimeUtc = Instant.parse(dateTimeUtc)
        .atZone(ZoneId.of("UTC")),
    distanceMeters = distanceMeters,
    location = Location(
        lat = latitude,
        long = longitude
    ),
    maxSpeedKmh = maxSpeedKmh,
    totalElevationMeters = totalElevationMeters,
    mapPictureUrl = mapPictureUrl,
    avgHeartRate = avgHeartRate,
    maxHeartRate = maxHeartRate
)

fun Run.toRunEntity() = RunEntity(
    id = id?: ObjectId().toHexString(),
    durationMillis = duration.inWholeMilliseconds,
    dateTimeUtc = dateTimeUtc.toInstant().toString(),
    distanceMeters = distanceMeters,
    latitude = location.lat,
    longitude = location.long,
    avgSpeedKmh = avgSpeedKmh,
    maxSpeedKmh = maxSpeedKmh,
    totalElevationMeters = totalElevationMeters,
    mapPictureUrl = mapPictureUrl,
    avgHeartRate = avgHeartRate,
    maxHeartRate = maxHeartRate
)